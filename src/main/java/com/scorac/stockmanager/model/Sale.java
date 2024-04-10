package com.scorac.stockmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="sale")
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false)
    private Long saleId;
    private LocalDate date;
    private int multiplicity;

    @ManyToOne
    @JoinColumn(name = "recipe")
    private Recipe recipe;
}
