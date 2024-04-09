package com.scorac.stockmanager.model.TDO;

import com.scorac.stockmanager.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class RecipeTDO {
    private String name;
    private List<ProductTDO> productList;
}
