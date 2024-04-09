package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.PrepService;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import com.scorac.stockmanager.service.Repository.StockRepository;
import com.scorac.stockmanager.service.StockService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

//    @PostMapping("/deleteExpire")
//    public String deleteExpireProduct(@RequestParam Long productId, HttpServletRequest request) {
//        System.out.println("Received productId: " + productId);
//
//        String currentUrl = request.getRequestURI();
//        // Return to the current page
//        return  "redirect:" + currentUrl;
//    }

    @PostMapping("/deleteExpire")
    public String handleDeleteExpire(@RequestParam("productId") Long productId) {
        // Handle the form submission
        System.out.println("Deleting product with ID: " + productId);
        prepService.deletePrep(productId);




        return "redirect:/index";
    }
}
