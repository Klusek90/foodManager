package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.Entity.Recipe;
import com.scorac.stockmanager.model.Entity.Sale;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.TDO.SaleDTO;
import com.scorac.stockmanager.model.TDO.Meal;
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

    public ChartDataRespose saleMonthly(LocalDate date){
        List<Sale> all = findAll();
        String[] label = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        float[] sale= new float[12];

         for (int i =0; i < all.size(); i++) {
            LocalDate itemDate =  all.get(i).getDate();
                if(itemDate.getYear() == date.getYear()){
                    for (int j = 0; j < 12; j++) {
                        if(itemDate.getMonth().getValue() == (j+1)){
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

    public ChartDataRespose saleWeekly(LocalDate date ) {
        List<Sale> all = findAll();
        float[] sale = new float[7]; // Automatically initialized to 0
        String[] label = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Iterate over each sale
        for (Sale saleItem : all) {
            LocalDate itemDate = saleItem.getDate();
            // Check if the sale date is within the past week and not in the future
            if (itemDate.isAfter(date.minusDays(7)) && !itemDate.isAfter(date)) {
                int dayOfWeekIndex = itemDate.getDayOfWeek().getValue() - 1; // Adjusting to 0-based index
                float price = saleItem.getRecipe().getPrice() * saleItem.getMultiplicity();
                sale[dayOfWeekIndex] += price;
            }
        }

        // After today, set future sales to 0 explicitly for clarity, even though they are already 0
        for (int i = date.getDayOfWeek().getValue(); i < 7; i++) {
            sale[i] = 0; // This ensures no future sales data is considered
        }

        ChartDataRespose chartData = new ChartDataRespose();
        chartData.setDataset(sale);
        chartData.setLabels(label);
        return chartData;
    }


    public Map<String, Meal> saleDaily(LocalDate date) {
        List<Sale> all = findAll();
        ArrayList<Meal> meals = new ArrayList<>();

        for(int i =0;i<all.size();i++){
            if(all.get(i).getDate().isEqual(date)){

                Meal meal = new Meal();
                meal.setName(all.get(i).getRecipe().getName());
                meal.setQuantity(all.get(i).getMultiplicity());
                meal.setPrice(all.get(i).getRecipe().getPrice());
                meals.add(meal);
            }
        }
        Map<String, Meal> mergedMeals = new HashMap<>();

        for (Meal meal : meals) {
            if (mergedMeals.containsKey(meal.getName())) {
                // If the map already contains a meal with the same name, merge them
                Meal existingMeal = mergedMeals.get(meal.getName());
                existingMeal.setQuantity(existingMeal.getQuantity() + meal.getQuantity());
                existingMeal.setPrice(existingMeal.getPrice() + meal.getPrice());
            } else {
                // If the map doesn't contain a meal with the same name, add it
                mergedMeals.put(meal.getName(), new Meal(meal.getName(), meal.getQuantity(), meal.getPrice()));
            }
        }

        return mergedMeals;
    }
}
