package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.model.TDO.ProductTDO;
import com.scorac.stockmanager.model.TDO.RecipeTDO;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import com.scorac.stockmanager.service.Repository.RecipeProductRepository;
import com.scorac.stockmanager.service.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeProductService {
    @Autowired
    private RecipeProductRepository recipeProductRepository;
    private RecipeService recipeService;
    private ProductService productService;

    public RecipeProductService(RecipeProductRepository recipeProductRepository, RecipeService recipeService, ProductService productService) {
        this.recipeProductRepository = recipeProductRepository;
        this.recipeService = recipeService;
        this.productService = productService;
    }

    public void save(RecipeProduct recipeProduct){
        recipeProductRepository.save(recipeProduct);
    }



    public RecipeTDO fullRecipe(Long id){
        List<RecipeProduct> recipeProduct = recipeProductRepository.findAllByRecipe_RecipeId(id);

        RecipeTDO recipeTDO= new RecipeTDO();
        recipeTDO.setName(recipeService.findRecipeNameById(id));
        List<ProductTDO> products= new ArrayList<>();

        for(int i =0; i < recipeProduct.size(); i++){
            ProductTDO productTDO = new ProductTDO();
            productTDO.setQuantity(recipeProduct.get(i).getQuantity());
            productTDO.setName(productService.findProductName(recipeProduct.get(i).getId()) );
            products.add(productTDO);
        }
        recipeTDO.setProductList(products);
        return recipeTDO;
    }
}
