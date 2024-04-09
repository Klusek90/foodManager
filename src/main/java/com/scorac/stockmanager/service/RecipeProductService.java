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
    private RecipeRepository recipeRepository;

    private ProductRepository productRepository;

    public RecipeProductService(RecipeProductRepository recipeProductRepository, RecipeRepository recipeRepository, ProductRepository productRepository) {
        this.recipeProductRepository = recipeProductRepository;
        this.recipeRepository = recipeRepository;
        this.productRepository = productRepository;
    }

    public void save(RecipeProduct recipeProduct){
        recipeProductRepository.save(recipeProduct);
    }

    public RecipeTDO fullRecipe(Long id){
        List<RecipeProduct> recipeProduct = recipeProductRepository.findByRecipeId(id);

        RecipeTDO recipeTDO= new RecipeTDO();
        recipeTDO.setName(recipeRepository.findNameByRecipeId(id));
        List<ProductTDO> products= new ArrayList<>();

        for(int i =0; i < recipeProduct.size(); i++){
            ProductTDO productTDO = new ProductTDO();
            productTDO.setQuantity(recipeProduct.get(i).getQuantity());
            productTDO.setName(productRepository.findNameByProductId(recipeProduct.get(i).getId()) );
            products.add(productTDO);
        }
        recipeTDO.setProductList(products);
        return recipeTDO;
    }
}
