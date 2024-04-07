package com.scorac.stockmanager.service.Repository;

import com.scorac.stockmanager.model.RecipeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Long> {
}
