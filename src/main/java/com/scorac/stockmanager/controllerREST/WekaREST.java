package com.scorac.stockmanager.controllerREST;
import com.scorac.stockmanager.service.WekaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
public class WekaREST {

    @Autowired
    private WekaService wekaService;

    @GetMapping("/predict")
    public Map<String, Double> predict() {
        LocalDate date = LocalDate.now();
//        return wekaService.preditionMap(date);
        Map<String, Double> newmap = new HashMap<>();
        newmap.put("Cooked spagetti", 180.0);
        newmap.put("Meat balls", 200.0);
        newmap.put("Pomodoro sauce", 145.0);
        newmap.put("Cooked penne", 280.0);
        newmap.put("Bolonese sauce", 235.0);
        newmap.put("Grated Cheese", 50.0);
        newmap.put("Rice", 230.0);
        newmap.put("Mince Beef", 360.0);
        newmap.put("Test", 0.0);



        return newmap;
    }
}
