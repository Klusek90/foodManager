package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class BigData {
        private Long productid;
        private float weatherTemp;
        private float weatherHumidity;
        private float weatherPressure;
        private int bookings;
        private int dayOfWeek;
        private int monthNumber;
        private int currentStock;

        public BigData(Long productid, float weatherTemp, float weatherHumidity, float weatherPressure, int bookings, int dayOfWeek, int monthNumber, int currentStock) {
            this.productid = productid;
            this.weatherTemp = weatherTemp;
            this.weatherHumidity = weatherHumidity;
            this.weatherPressure = weatherPressure;
            this.bookings = bookings;
            this.dayOfWeek = dayOfWeek;
            this.monthNumber = monthNumber;
            this.currentStock = currentStock;
        }

        public BigData() {
    }
}
