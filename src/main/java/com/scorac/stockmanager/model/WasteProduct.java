//package com.scorac.stockmanager.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "waste_product")
//public class WasteProduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "waste_id", nullable = false)
//    private Waste waste;
//
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "product_id", nullable = false)
////    private Product product;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    @Column(name = "value")
//    private float value;
//}