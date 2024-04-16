package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.service.WekaService;
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

    @GetMapping("/createcsv")
    public String createCSV(){
        wekaService.writeListToCSV();
        return "done";
    }

    @GetMapping("/trainAlgorithm")
    public String trainWeka() {
        return wekaService.trainAndEvaluateModel();
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
    public String predict(@RequestParam double temp, @RequestParam double humidity, @RequestParam double pressure,
                          @RequestParam int bookings, @RequestParam int dayOfWeek, @RequestParam int month, @RequestParam int waste) {
        try {
            Instance instance = wekaService.createInstance(temp, humidity, pressure, bookings, dayOfWeek, month, waste);
            double prediction = wekaService.makePrediction(instance);
            return "Predicted class value: " + prediction;
        } catch (Exception e) {
            return "Prediction failed: " + e.getMessage();
        }
    }
}
