package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Entity.Sale;
import com.scorac.stockmanager.service.SaleService;
import com.scorac.stockmanager.service.WekaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
public class reportConrol {

    private SaleService saleService;

    private WekaService wekaService;

    public reportConrol(SaleService saleService, WekaService wekaService) {
        this.saleService = saleService;
        this.wekaService = wekaService;
    }

    @GetMapping("/main")
    public String reports(){
        return "reports";
    }

    @GetMapping("/sale")
    public String saleReport() {
        return "reports/reportSale";
    }

    @GetMapping("/waste")
    public String wasteReport() {
        return "reports/reportWaste";
    }

    @GetMapping("/expire")
    public String expireReport() {
        return "reports/reportExpire";
    }

    @GetMapping("/prediction")
    public String predictionReport(Model model){
        LocalDate date = LocalDate.now();
//        BigData data = new BigData(1, 15.00, 65.00, 1010.00, 100, 4, 11, 50, 500, 0);
        Map<String,Double> productPredictions= wekaService.preditionMap(date);
        model.addAttribute("productPredictions", productPredictions);
        return  "reports/predictions";}
}
