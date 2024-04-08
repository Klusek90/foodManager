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

    public Stock(String name, String type, int amount, LocalDate productiondate, LocalDate expireDate, int daysLeft) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.productiondate = productiondate;
        this.expireDate = expireDate;
        this.daysLeft = daysLeft;
    }

    private int daysLeft;


    public Stock() {
    }
}
