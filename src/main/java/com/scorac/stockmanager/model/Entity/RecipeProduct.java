package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="recipe_product")
public class RecipeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
