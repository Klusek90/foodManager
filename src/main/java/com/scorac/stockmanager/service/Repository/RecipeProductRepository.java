package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Long> {

   List<RecipeProduct> findAllByRecipe_RecipeId(Long id);
}
