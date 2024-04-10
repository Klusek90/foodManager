package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class ProductTDO {

    private Long productid;
    private String name;
    private int quantity;

    public ProductTDO(Long productid, String name, int quantity) {
        this.productid = productid;
        this.name = name;
        this.quantity = quantity;
    }

    public ProductTDO() {
    }
}
