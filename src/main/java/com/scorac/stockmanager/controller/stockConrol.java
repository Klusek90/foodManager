package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.ProductRepository;
import com.scorac.stockmanager.service.StockRepository;
import com.scorac.stockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stock")
public class stockConrol {
    @Autowired
    private final StockRepository stockRepository;
    private final StockService stockService;

    private ProductRepository productRepository;

    public stockConrol(StockRepository stockRepository, StockService stockService, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
        this.productRepository =productRepository;
    }

    @GetMapping("/total")
    public String stockContorl(Model model){
        return "stock";
    }


    @GetMapping("/datatable")
    @ResponseBody   //restcontrol
    public List<Product> getForm(final @RequestParam Map<String, String> allRequestParams){
       List<Product> stock = productRepository.findAll();

//        LocalDate date = LocalDate.now();
//        LocalDate nexday = date.minusDays(3);
//        Stock prod1= new Stock("ogorek", "kielbasa", 20, date);
//        Stock prod2= new Stock("sd", "kielbassda", 14, nexday);
////        model.addAttribute("total", stock);
//        stock.add(prod1);
//        stock.add(prod2);
        return stock;
//        return stockService.listofALLSortedByName();
    }
}
