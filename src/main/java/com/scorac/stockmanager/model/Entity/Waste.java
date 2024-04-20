package com.scorac.stockmanager.model.Entity;

import com.scorac.stockmanager.model.Entity.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="waste")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    private LocalDate date;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Waste(Long id, LocalDate date, int quantity, Product product) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.product = product;
    }

    public Waste() {
    }
}
