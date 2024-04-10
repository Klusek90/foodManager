package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.Sale;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.TDO.SaleDTO;
import com.scorac.stockmanager.service.Repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    private PrepService prepService;
    private RecipeService recipeService;
    private RecipeProductService recipeProductService;
    private static final Logger logger = LoggerFactory.getLogger(SaleService.class);


    public SaleService(PrepService prepService, RecipeService recipeService, RecipeProductService recipeProductService) {
        this.prepService = prepService;
        this.recipeService = recipeService;
        this.recipeProductService = recipeProductService;
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
           logger.info("Sale added");
           return "Sale processed";

       } catch (Exception e){
           logger.error("Error message");
           return "Faild to procees";
       }

    }


}
