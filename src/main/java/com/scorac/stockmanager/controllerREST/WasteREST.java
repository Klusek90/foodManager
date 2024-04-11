package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.TDO.Meal;
import com.scorac.stockmanager.service.SaleService;
import com.scorac.stockmanager.service.WasteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class WasteREST {
     private WasteService wasteService;

    public WasteREST(WasteService wasteService) {
        this.wasteService = wasteService;
    }

    @GetMapping("/monthWaste/{date}")
    public ChartDataRespose yearWaste(@PathVariable LocalDate date){
        ChartDataRespose monthly = wasteService.monthlyWaste(date);
        return monthly;
    }

    @GetMapping("/weekWaste/{date}")
    public ChartDataRespose weekWaste(@PathVariable LocalDate date){
        ChartDataRespose weekly= wasteService.weeklyWaste(date);
        return weekly;
    }

//    @GetMapping("/dailyWaste/{date}")
//    public Map<String, Meal> dailyWaste(@PathVariable LocalDate date){
//        Map<String, Meal> daily= wasteService.dailyWaste(date);
//        return daily;
//    }
}
