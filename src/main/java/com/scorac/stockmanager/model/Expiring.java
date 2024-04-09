package com.scorac.stockmanager.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Expiring {
    private String name;
    private Long productid;
    private int daysLeft;

    private LocalDate created;

    private  LocalDate expire;

    private Long prepid;

    public Expiring() {
    }

    public Expiring(String name, Long productid, int daysLeft, LocalDate created, LocalDate expire) {
        this.name = name;
        this.productid = productid;
        this.daysLeft = daysLeft;
        this.created = created;
        this.expire = expire;
    }
}
