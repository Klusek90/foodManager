package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    private String name;
    private int lifeLength;
    private String type;

    public Product(Long id, String name, int lifeLength, String type) {
        this.id = id;
        this.name = name;
        this.lifeLength = lifeLength;
        this.type = type;
    }

    public Product() {
    }
}
