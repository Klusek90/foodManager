package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.service.WekaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

import java.time.LocalDate;

@RestController
public class WekaREST {

    private WekaService wekaService;

    public WekaREST(WekaService wekaService) {
        this.wekaService = wekaService;
    }

    @GetMapping("/trainModel")
    public String trainModel() {
        try {
            wekaService.trainAndEvaluateModel();
            return "Model trained and evaluated successfully!";
        } catch (Exception e) {
            return "Error in model training: " + e.getMessage();
        }
    }
    @GetMapping("/loadModel")
    public String loadModel() {
        try {
            wekaService.loadModel("model.model");
            return "Model loaded successfully.";
        } catch (Exception e) {
            return "Failed to load model: " + e.getMessage();
        }
    }

    @GetMapping("/predict")
    public ResponseEntity<?> predict(@RequestParam double temp, @RequestParam double humidity, @RequestParam double pressure,
                                     @RequestParam int bookings, @RequestParam int dayOfWeek, @RequestParam int month, @RequestParam int waste) {
        try {
            Instance instance = wekaService.createInstance(temp, humidity, pressure, bookings, dayOfWeek, month, waste);
            double prediction = wekaService.makePrediction(instance);
            return ResponseEntity.ok("Predicted value: " + prediction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Prediction failed: " + e.getMessage());
        }
    }
}
