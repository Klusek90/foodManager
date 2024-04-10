package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.PrepService;
import com.scorac.stockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class stockConrol {
    @Autowired
    private StockService stockService;
    private PrepService prepService;

    public stockConrol(StockService stockService, PrepService prepService) {
        this.stockService = stockService;
        this.prepService = prepService;
    }

    @GetMapping("/total")
    public String stockContorl(Model model){
        return "stock";
    }


    @GetMapping("/datatable")
    @ResponseBody   //restcontrol
    public List<Stock> getForm(){
        List<Stock> stock = stockService.currentStock();
        return stock;
    }
    @PostMapping("/deleteExpire")
    public String handleDeleteExpire(@RequestParam("productId") Long productId) {
        // Handle the form submission
        System.out.println("Deleting product with ID: " + productId);
        prepService.deletePrep(productId);
        return "redirect:/index";
    }
}
