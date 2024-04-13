package com.scorac.stockmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagerApplication.class, args);
    }

//
//     try {
//        List<BigData> dataList = bigDataSet(); // Assuming this method returns your data
//        writeListToCSV(dataList, "path/to/your/output.csv");
//        System.out.println("Data exported successfully.");
//    } catch (IOException e) {
//        System.out.println("Error writing to CSV: " + e.getMessage());
//    }
}
