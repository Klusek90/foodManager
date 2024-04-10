package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    String findNameByRecipeId(Long recipeId);

    Recipe findByRecipeId(Long id);
}
