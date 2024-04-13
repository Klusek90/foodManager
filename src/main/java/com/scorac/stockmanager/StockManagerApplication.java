package com.scorac.stockmanager;

import com.scorac.stockmanager.model.BigData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class StockManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagerApplication.class, args);
    }


}
