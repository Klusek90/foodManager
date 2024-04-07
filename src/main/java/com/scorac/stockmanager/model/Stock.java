package com.scorac.stockmanager.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Stock {

    private String name;
    private String type;
    private int amount;
    private LocalDate date;

    public Stock(String name, String type, int amount, LocalDate date) {
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Stock() {
    }
}
