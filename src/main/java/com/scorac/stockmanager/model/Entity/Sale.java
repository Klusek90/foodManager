package com.scorac.stockmanager.model.Entity;

import com.scorac.stockmanager.model.Entity.Recipe;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

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
