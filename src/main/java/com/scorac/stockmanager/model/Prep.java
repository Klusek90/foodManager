package com.scorac.stockmanager.model;

import jakarta.persistence.*;
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

}
