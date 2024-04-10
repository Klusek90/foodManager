package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class ProductDTO {

    private Long productid;
    private String name;
    private int quantity;

    public ProductDTO(Long productid, String name, int quantity) {
        this.productid = productid;
        this.name = name;
        this.quantity = quantity;
    }

    public ProductDTO() {
    }
}
