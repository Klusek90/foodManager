package com.scorac.stockmanager.model.TDO;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDTO {
    private String name;
    private List<ProductDTO> productList;

    public RecipeDTO(String name, List<ProductDTO> productList) {
        this.name = name;
        this.productList = productList;
    }

    public RecipeDTO() {
    }
}
