package com.scorac.stockmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="product_quantity")
public class ProductQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private int status; //1 sale 2 waste 3 order

    private  int quantity;

    private LocalDate date;

    private Long statusId;

}
