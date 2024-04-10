package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.Entity.Recipe;
import com.scorac.stockmanager.model.Entity.Sale;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.TDO.SaleDTO;
import com.scorac.stockmanager.model.TDO.TodayMeal;
import com.scorac.stockmanager.service.Repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    private PrepService prepService;
    private RecipeService recipeService;
    private RecipeProductService recipeProductService;
    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);


    private LocalDate today;

    public SaleService(PrepService prepService, RecipeService recipeService, RecipeProductService recipeProductService) {
        this.prepService = prepService;
        this.recipeService = recipeService;
        this.recipeProductService = recipeProductService;

    }

    public List<Sale> findAll(){
        List<Sale> all = saleRepository.findAll();
        return all;
    }

    public String save(SaleDTO saleTDO){
       try{
           LocalDate timestamp = LocalDate.now();
           Recipe recipe = recipeService.findRecipe(saleTDO.getRecipeid());
           List<ProductDTO> productList = recipeProductService.fullRecipe(saleTDO.getRecipeid()).getProductList();
           for(int i = 0; i < productList.size(); i++){

               ProductDTO product =productList.get(i);
               int newQuantity = product.getQuantity() * saleTDO.getMultiplicity();
               product.setQuantity(newQuantity);
               prepService.updatestock(product);
           }
           Sale sale= new Sale();
           sale.setDate(timestamp);
           sale.setMultiplicity(saleTDO.getMultiplicity());
           sale.setRecipe(recipe);
           saleRepository.save(sale);

           logger.info("Sale added");
           return "Sale processed";

       } catch (Exception e){
           logger.error("Error message");
           return "Faild to procees";
       }

    }

    public ChartDataRespose saleMonthly(){
        today= LocalDate.now();
        List<Sale> all = findAll();
        String[] label = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        float[] sale= new float[12];

         for (int i =0; i < all.size(); i++) {
            LocalDate date =  all.get(i).getDate();
                if(date.getYear() == today.getYear()){
                    for (int j = 0; j < 12; j++) {
                        if(date.getMonth().getValue() == (j+1)){
                            float price = all.get(i).getRecipe().getPrice() * all.get(i).getMultiplicity();
                            sale[j] += price;
                        }
                }
             }
         }
         ChartDataRespose chartdata = new ChartDataRespose();
         chartdata.setLabels(label);
         chartdata.setDataset(sale);

        return chartdata;
    }

    public ChartDataRespose saleWeekly(){
        today= LocalDate.now();
        List<Sale> all = findAll();
        float[] sale= new float[7];
        String[] label= {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

         for (int i =0; i < all.size(); i++) {
            LocalDate date =  all.get(i).getDate();
            if(date.isAfter(today.minusWeeks(1))){
                for (int j = 0; j < date.getDayOfWeek().getValue(); j++) {
                    if(date.getDayOfWeek().getValue()== (j+1)){
                        float price = all.get(i).getRecipe().getPrice() * all.get(i).getMultiplicity();
                        sale[j] += price;
                        }
                    }
                }
            }

         ChartDataRespose chartdata= new ChartDataRespose();
         chartdata.setDataset(sale);
         chartdata.setLabels(label);
        return chartdata;
    }


    public Map<String, Integer> saleDaily() {
        today= LocalDate.now();
        List<Sale> all = findAll();
        ArrayList<TodayMeal> names = new ArrayList<>();
        for(int i =0;i<all.size();i++){
            if(all.get(i).getDate().isEqual(today)){
                TodayMeal meal = new TodayMeal();
                meal.setName(all.get(i).getRecipe().getName());
                meal.setQuantity(all.get(i).getMultiplicity());
            }
        }

        // Use a map to sum quantities by name
        Map<String, Integer> recipeSums = new HashMap<>();
        for (TodayMeal name : names) {
            recipeSums.merge(name.getName(), name.getQuantity(), Integer::sum);
        }

        return recipeSums;
    }
}
