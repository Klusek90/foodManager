package com.scorac.stockmanager.model.TDO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WeatherDTO {

    private float temp;
    private String description;

    public WeatherDTO() {
    }

    public WeatherDTO(float temp, String description) {
        this.temp = temp;
        this.description = description;
    }
}
