package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="recipeid", nullable = false)
    private Long recipeId;
    private String name;
    private float price;

}
