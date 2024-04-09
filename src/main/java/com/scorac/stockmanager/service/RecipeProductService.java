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

    public List<RecipeProduct> listforRecipe(Long id){
        return recipeProductRepository.findAllByRecipe_RecipeId(id);
    }

    public RecipeTDO fullRecipe(Long id){
        List<RecipeProduct> recipeProduct = listforRecipe(id);
        RecipeTDO recipeTDO= new RecipeTDO();
        List<ProductTDO> products= new ArrayList<>();

        for(int i =0; i < recipeProduct.size(); i++){
            ProductTDO productTDO = new ProductTDO();
            productTDO.setQuantity(recipeProduct.get(i).getQuantity());
            productTDO.setName(recipeProduct.get(i).getProduct().getName());
            products.add(productTDO);
        }
        recipeTDO.setName(recipeProduct.get(0).getRecipe().getName());
        recipeTDO.setProductList(products);
        return recipeTDO;
    }
}
