package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class BigData {

    private Long productid;
    private float weatherTemp;
    private float weatherHumidity;
    private float weatherPressure;
    private int madeQuantity;
    private int saleQuantity;
    private int bookings;
    private int dayOfWeek;
    private int monthNumber;
    private int waste;

    public BigData(Long productid, float weatherTemp, float weatherHumidity, float weatherPressure, int madeQuantity, int saleQuantity, int bookings, int dayOfWeek, int monthNumber, int waste) {
        this.productid = productid;
        this.weatherTemp = weatherTemp;
        this.weatherHumidity = weatherHumidity;
        this.weatherPressure = weatherPressure;
        this.madeQuantity = madeQuantity;
        this.saleQuantity = saleQuantity;
        this.bookings = bookings;
        this.dayOfWeek = dayOfWeek;
        this.monthNumber = monthNumber;
        this.waste = waste;
    }

    public BigData() {
    }
}
