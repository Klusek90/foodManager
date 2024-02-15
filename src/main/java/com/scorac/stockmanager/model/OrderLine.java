package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class OrderLine {
    private int lineId;
    private String itemName;
    private int quantity;

    public OrderLine(int lineId, String itemName, int quantity) {
        this.lineId = lineId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

}
