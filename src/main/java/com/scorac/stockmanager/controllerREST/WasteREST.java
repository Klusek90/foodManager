package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.TDO.Meal;
import com.scorac.stockmanager.service.SaleService;
import com.scorac.stockmanager.service.WasteService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public ChartDataRespose yearWaste(@PathVariable(name="date") LocalDate date){
        ChartDataRespose monthly = wasteService.monthlyWaste(date);
        return monthly;
    }

    @GetMapping("/weekWaste/{date}")
    public ChartDataRespose weekWaste(@PathVariable(name="date") LocalDate date){
        ChartDataRespose weekly= wasteService.weeklyWaste(date);
        return weekly;
    }

    @GetMapping("/dailyWaste/{date}")
    public Map<String, Integer> dailyWaste(@PathVariable(name="date") LocalDate date){
        Map<String, Integer> daily= wasteService.dailyWaste(date);
        return daily;
    }
}
