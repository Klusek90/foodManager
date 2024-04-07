//package com.scorac.stockmanager.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//@Table(name = "order_product")
//public class OrderProduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
//
////    @ManyToOne
////    @JoinColumn(name = "product_id")
////    private Product product;
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//}