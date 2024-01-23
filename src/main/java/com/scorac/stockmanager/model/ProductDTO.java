package com.scorac.stockmanager.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private String type;
    private BigDecimal weight;
    private BigDecimal cost;
    private String other;
}
