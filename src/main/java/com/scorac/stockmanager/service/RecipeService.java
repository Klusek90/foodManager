package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Entity.Recipe;
import com.scorac.stockmanager.service.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getAll(){
        List<Recipe> all = recipeRepository.findAll();
        return all;
    }
    public void save(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public String findRecipeNameById(Long recipeId) {
        return recipeRepository.findNameByRecipeId(recipeId);
    }

    public Recipe findRecipe(Long id){
        return recipeRepository.findByRecipeId(id);
    }

}
