package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class Weather {

    private String temp;
    private String description;

    public Weather(String temp, String description) {
        this.temp = temp;
        this.description = description;
    }

    public Weather() {
    }
}
