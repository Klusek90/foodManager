package com.scorac.stockmanager.model.TDO;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    private String name;
    private List<ProductDTO> productList;
    private float price;


    public RecipeDTO() {
    }
}
