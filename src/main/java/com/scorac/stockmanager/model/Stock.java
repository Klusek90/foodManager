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
    private int daysLeft;


    public Stock() {
    }
}
