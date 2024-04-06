package com.scorac.stockmanager.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class RecipeProduct implements Serializable {
    private Long recipe;
    private Long product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeProduct that = (RecipeProduct) o;
        return Objects.equals(recipe, that.recipe) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, product);
    }
}