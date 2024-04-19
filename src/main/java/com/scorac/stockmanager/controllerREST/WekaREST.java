package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.service.WekaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weka.core.Instances;

import java.util.Map;


@RestController
public class WekaREST {

    @Autowired
    private WekaService wekaService;

    @GetMapping("/predict")
    public String predict() {
        return wekaService.performPrediction();
    }
}
