package com.scorac.stockmanager.controllerREST;

import com.scorac.stockmanager.model.ChartDataRespose;
import com.scorac.stockmanager.model.TDO.Meal;
import com.scorac.stockmanager.service.SaleService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SaleREST {
     private SaleService saleService;

    public SaleREST(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/monthSale")
    public ChartDataRespose yearSale(){
        ChartDataRespose monthly = saleService.saleMonthly();
        return monthly;
    }

    @GetMapping("/weekSale")
    public ChartDataRespose weekSale(){
        ChartDataRespose weekly= saleService.saleWeekly();
        return weekly;
    }

    @GetMapping("/dailySale")
    public Map<String, Meal> dailySale(){
        Map<String, Meal> daily= saleService.saleDaily();
        return daily;
    }
}
