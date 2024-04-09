package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class ProductTDO {
    private String name;
    private int quantity;

    public ProductTDO(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public ProductTDO() {
    }
}
