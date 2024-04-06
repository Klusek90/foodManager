package com.scorac.stockmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name="recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="recipeid", nullable = false)
    private Long recipeId;
    private String name;

    @ManyToMany
    private Set<Product> products;

    private String user;
}
