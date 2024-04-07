package com.scorac.stockmanager.model;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Recipe;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_product")
public class RecipeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Additional field
    private Integer quantity;

}
