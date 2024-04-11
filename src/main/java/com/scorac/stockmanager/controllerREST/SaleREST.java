package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.TDO.Meal;
import com.scorac.stockmanager.service.SaleService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class SaleREST {
     private SaleService saleService;

    public SaleREST(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/monthSale/{date}")
    public ChartDataRespose yearSale(@PathVariable LocalDate date){
        ChartDataRespose monthly = saleService.saleMonthly(date);
        return monthly;
    }

    @GetMapping("/weekSale/{date}")
    public ChartDataRespose weekSale(@PathVariable LocalDate date){
        ChartDataRespose weekly= saleService.saleWeekly(date);
        return weekly;
    }

    @GetMapping("/dailySale/{date}")
    public Map<String, Meal> dailySale(@PathVariable LocalDate date){
        Map<String, Meal> daily= saleService.saleDaily(date);
        return daily;
    }
}
