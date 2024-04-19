package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="prep")
public class Prep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate productionDate;
    private LocalDate expireDate;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public Prep() {

    }

    public Prep(Long id, LocalDate productionDate, LocalDate expireDate, int amount, Product product) {
        this.id = id;
        this.productionDate = productionDate;
        this.expireDate = expireDate;
        this.amount = amount;
        this.product = product;
    }
}
