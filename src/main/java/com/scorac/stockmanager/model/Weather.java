package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class Weather {
    private String description;
    private String temperature;

    public Weather(String description, String temperature) {
        this.description = description;
        this.temperature = temperature;
    }

    public Weather() {
    }
}
