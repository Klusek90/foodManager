package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class BigData {
        private int productId;
        private float temperature;
        private float humidity;
        private float pressure;
        private int bookings;
        private int dayOfWeek;
        private int monthNumber;
        private int madeQuantity;
        private int wasteQuantity;

    public BigData(int productId, float temperature, float humidity, float pressure, int bookings, int dayOfWeek, int monthNumber, int madeQuantity, int wasteQuantity) {
        this.productId = productId;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.bookings = bookings;
        this.dayOfWeek = dayOfWeek;
        this.monthNumber = monthNumber;
        this.madeQuantity = madeQuantity;
        this.wasteQuantity = wasteQuantity;
    }

    public BigData(long l, double v, double v1, double v2, int i, int i1, int i2, int i3, int i4, int i5) {
    }
}
