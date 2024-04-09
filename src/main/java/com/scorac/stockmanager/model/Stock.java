package com.scorac.stockmanager.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Stock {
    private String name;
    private String type;
    private int amount;
    private LocalDate productiondate;
    private LocalDate expireDate;

    private Long productid;
    private int daysLeft;

    public Stock(String name, String type, int amount, LocalDate productiondate, LocalDate expireDate, int daysLeft, Long productid) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.productiondate = productiondate;
        this.expireDate = expireDate;
        this.daysLeft = daysLeft;
        this.productid = productid;
    }

    public Stock() {
    }
}
