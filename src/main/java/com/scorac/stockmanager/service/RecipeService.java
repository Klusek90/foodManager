package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void save(Recipe recipe){
        recipeRepository.save(recipe);
    }
}
