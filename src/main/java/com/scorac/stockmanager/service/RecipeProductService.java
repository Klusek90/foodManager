package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.service.Repository.RecipeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeProductService {
    @Autowired
    private RecipeProductRepository recipeProductRepository;

    public RecipeProductService(RecipeProductRepository recipeProductRepository) {
        this.recipeProductRepository = recipeProductRepository;
    }

    public void save(RecipeProduct recipeProduct){
        recipeProductRepository.save(recipeProduct);
    }
}
