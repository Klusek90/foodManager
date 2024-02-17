package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class Weather {

    private String temp;
    private String description;
    private String date;

    public Weather(String temp, String description, String date) {
        this.temp = temp;
        this.description = description;
        this.date=date;
    }

    public Weather() {
    }
}
