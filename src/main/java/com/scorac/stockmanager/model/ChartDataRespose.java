package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class ChartDataRespose {
    private String[] labels;
    private float[] dataset;

}
