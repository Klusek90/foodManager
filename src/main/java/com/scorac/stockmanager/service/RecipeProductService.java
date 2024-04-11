package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.RecipeProduct;
import com.scorac.stockmanager.model.TDO.ProductDTO;
import com.scorac.stockmanager.model.TDO.RecipeDTO;
import com.scorac.stockmanager.service.Repository.RecipeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeProductService {
    @Autowired
    private RecipeProductRepository recipeProductRepository;

    public void save(RecipeProduct recipeProduct){
        recipeProductRepository.save(recipeProduct);
    }

    public List<RecipeProduct> listforRecipe(Long id){
        return recipeProductRepository.findAllByRecipe_RecipeId(id);
    }

    public RecipeDTO fullRecipe(Long id){
        List<RecipeProduct> recipeProduct = listforRecipe(id);
        RecipeDTO recipeTDO= new RecipeDTO();
        List<ProductDTO> products= new ArrayList<>();
        if( recipeProduct.size()>0){
            for(int i =0; i < recipeProduct.size(); i++){
                ProductDTO productTDO = new ProductDTO();
                productTDO.setQuantity(recipeProduct.get(i).getQuantity());
                productTDO.setName(recipeProduct.get(i).getProduct().getName());
                productTDO.setProductid(recipeProduct.get(i).getProduct().getId());
                products.add(productTDO);
            }
            recipeTDO.setName(recipeProduct.get(0).getRecipe().getName());
            recipeTDO.setProductList(products);
            recipeTDO.setPrice(recipeProduct.get(0).getRecipe().getPrice());
            return recipeTDO;
        }
        else {
            return recipeTDO;
        }
    }


}
