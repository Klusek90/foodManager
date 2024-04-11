package com.scorac.stockmanager.model;

import com.scorac.stockmanager.model.TDO.Meal;
import lombok.Data;

import java.util.List;

@Data
public class ChartDataRespose {
    private String[] labels;
    private float[] dataset;
    private List<Meal> meal;

}
