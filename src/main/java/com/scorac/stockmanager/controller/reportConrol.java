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
    public String predictionReport(){ return  "reports/predictions";}
}
