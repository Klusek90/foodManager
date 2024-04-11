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
    public String saleReport(Model model) {
//      List<Sale> sales= saleService.findAll();
//       model.addAttribute("sales" , sales);
        return "reports/reportSale";
    }

    @GetMapping("/waste")
    public String wasteReport(Model model) {
//        List<Sale> sales= saleService.findAll();
//        model.addAttribute("sales" , sales);
        return "reports/reportSale";
    }

}
