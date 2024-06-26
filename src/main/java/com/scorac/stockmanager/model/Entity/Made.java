package com.scorac.stockmanager.model.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "made")
public class Made {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private LocalDate created;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
