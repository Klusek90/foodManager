package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class Meal {
    private String name;
    private int quantity;
    private float price;

    public Meal(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Meal(){}


}
