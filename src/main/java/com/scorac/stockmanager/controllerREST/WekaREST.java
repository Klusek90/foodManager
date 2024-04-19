package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.BigData;
import com.scorac.stockmanager.service.WekaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weka.core.Instances;

import java.time.LocalDate;
import java.util.Map;


@RestController
public class WekaREST {

    @Autowired
    private WekaService wekaService;

    @GetMapping("/predict")
    public Map<String, Double> predict() {
        LocalDate date = LocalDate.now();
//        BigData data = new BigData(1, 15.00, 65.00, 1010.00, 100, 4, 11, 50, 500, 0);
        return wekaService.preditionMap(date);
    }
}
