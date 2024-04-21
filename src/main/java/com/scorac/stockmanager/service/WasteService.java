package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.Entity.Sale;
import com.scorac.stockmanager.model.TDO.Meal;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.Entity.Waste;
import com.scorac.stockmanager.service.Repository.WasteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WasteService {

    @Autowired
    private WasteRepository wasteRepository;
    private PrepService prepService;
    private ProductService productService;

    public WasteService(PrepService prepService, ProductService productService) {
        this.prepService = prepService;
        this.productService = productService;
    }

    private static final Logger logger = LoggerFactory.getLogger(WasteService.class);

    public List<Waste> findAll(){
        return wasteRepository.findAll();
    }

    public  String saveWaste(List<Product> products, List<Integer> quantities){
        try{
            LocalDate datestamp = LocalDate.now();
            for(int i =0 ; i< products.size(); i++){

                Waste waste= new Waste();
                waste.setDate(datestamp);
                waste.setProduct(products.get(i));
                waste.setQuantity(quantities.get(i));
                wasteRepository.save(waste);

                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(products.get(i).getName());
                productDTO.setQuantity(quantities.get(i));
                productDTO.setProductid(products.get(i).getId());
                prepService.updatestock(productDTO);
                logger.info("Wastage added");

            }
            return "Product waste succesfuly";

        }catch (Exception e){
            logger.error("Fail to add wastage");
            return "Faild to process";
        }

    }


    public ChartDataRespose monthlyWaste(LocalDate date){
        List<Waste> all = findAll();
        String[] label = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        float[] quantity= new float[12];
        for(int i =0; i < all.size(); i++){
            LocalDate itemDate =  all.get(i).getDate();
            if(date.getYear() == itemDate.getYear()){
                for(int j =0; j< 12;j++){
                    if(itemDate.getMonth().getValue() == (j+1)){
                        int wasteValue = all.get(i).getQuantity();
                        quantity[j] += wasteValue;
                    }
                }
            }
        }
        ChartDataRespose response = new ChartDataRespose();
        response.setLabels(label);
        response.setDataset(quantity);
        return response;
    }
    public ChartDataRespose weeklyWaste(LocalDate date){
        List<Waste> all = findAll();
        float[] quantity = new float[7]; // Automatically initialized to 0
        String[] label = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (Waste waste : all) {
            LocalDate itemDate = waste.getDate();
            // Check if the sale date is within the past week and not in the future
            if (itemDate.isAfter(date.minusDays(7)) && !itemDate.isAfter(date)) {
                int dayOfWeekIndex = itemDate.getDayOfWeek().getValue() - 1; // Adjusting to 0-based index
                int wasteValue = waste.getQuantity();
                quantity[dayOfWeekIndex] += wasteValue;
            }
        }

        // After today, set future sales to 0 explicitly for clarity, even though they are already 0
        for (int i = date.getDayOfWeek().getValue(); i < 7; i++) {
            quantity[i] = 0; // This ensures no future sales data is considered
        }



        ChartDataRespose response = new ChartDataRespose();
        response.setLabels(label);
        response.setDataset(quantity);
        return response;
    }


    public Map<String, Integer> dailyWaste(LocalDate date){
        List<Waste> all = findAll();
        List<Meal> wastes =  new ArrayList<>();
        for (int i=0 ;i< all.size(); i++){
            if(date.isEqual(all.get(i).getDate())){
                Meal object = new Meal();
                object.setName(all.get(i).getProduct().getName());
                object.setQuantity(all.get(i).getQuantity());
                wastes.add(object);
            }
        }

        Map<String, Integer> todayWaste = new HashMap<>();
        for (Meal waste : wastes) {
            String name = waste.getName();
            int quantity = waste.getQuantity();
            if (todayWaste.containsKey(name)) {
                // If the name already exists in the map, add the quantity to the existing value
                int existingQuantity = todayWaste.get(name);
                todayWaste.put(name, existingQuantity + quantity);
            } else {
                // If the name doesn't exist in the map, add it with the quantity as the value
                todayWaste.put(name, quantity);
            }
        }

        return todayWaste ;
    }

  }
