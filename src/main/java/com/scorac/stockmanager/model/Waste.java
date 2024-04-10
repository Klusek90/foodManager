package com.scorac.stockmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name="waste")
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false)
    private Long id;
    private Date date;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
