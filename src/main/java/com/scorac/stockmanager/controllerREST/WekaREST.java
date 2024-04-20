package com.scorac.stockmanager.controllerREST;
import com.scorac.stockmanager.service.WekaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;


@RestController
public class WekaREST {

    @Autowired
    private WekaService wekaService;

    @GetMapping("/predict")
    public Map<String, Double> predict() {
        LocalDate date = LocalDate.now();
        return wekaService.preditionMap(date);
    }
}
