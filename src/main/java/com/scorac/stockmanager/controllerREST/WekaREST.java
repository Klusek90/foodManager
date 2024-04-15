package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.service.WekaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weka.classifiers.Classifier;
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
            Classifier model = wekaService.loadModel("model.model");
            // Here will be specific actions model will do after loading, such as predictions
            return "Model loaded successfully.";
        } catch (Exception e) {
            return "Failed to load model: " + e.getMessage();
        }
    }
}
