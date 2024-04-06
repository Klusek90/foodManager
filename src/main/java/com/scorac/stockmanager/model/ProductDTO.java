package com.scorac.stockmanager.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String name;
    private String lifeLength;
    private String type;

}
