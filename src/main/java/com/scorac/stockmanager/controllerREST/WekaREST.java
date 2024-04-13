package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.service.WekaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class WekaREST {

    private WekaService wekaService;

    public WekaREST(WekaService wekaService) {
        this.wekaService = wekaService;
    }

    @GetMapping("/createcsv")
    public String yearWaste(){
        wekaService.createCSV();
        return "done";
    }
}
