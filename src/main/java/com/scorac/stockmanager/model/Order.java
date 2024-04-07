//package com.scorac.stockmanager.model;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "order")
//@Data
//    public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "order_id", nullable = false)
//    private Long orderId;
//
//    private String deliveryName;
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderProduct> orderProducts;
//    private Date orderDate;
//    private Date deliveryDate;
//    private BigDecimal cost;
//
//
//
//
//
//
//}
