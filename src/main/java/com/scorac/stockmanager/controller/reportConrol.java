package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Entity.Sale;
import com.scorac.stockmanager.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reports")
public class reportConrol {

    private SaleService saleService;

    public reportConrol(SaleService saleService) {
        this.saleService = saleService;
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
}
