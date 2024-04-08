package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Stock;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import com.scorac.stockmanager.service.Repository.StockRepository;
import com.scorac.stockmanager.service.StockService;
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

    public stockConrol(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/total")
    public String stockContorl(Model model){
        return "stock";
    }


    @GetMapping("/datatable")
    @ResponseBody   //restcontrol
    public List<Stock> getForm(final @RequestParam Map<String, String> allRequestParams){
        List<Stock> stock = stockService.currentStock();
//        List<Stock> stock = new ArrayList<>();
        LocalDate now = LocalDate.now();
        stock.add(new Stock("empty", "stock", 0, now, now,0));

//       stock.add(new Stock("Kielbasa", "browary", 200, now, now.minusDays(3),2));
//       stock.add(new Stock("Kielbasa", "browary", 200, now, now.minusDays(3),2));
//       stock.add(new Stock("Kielbasa", "browary", 200, now, now.minusDays(3),2));
//       stock.add(new Stock("Kielbasa", "browary", 200, now, now.minusDays(3),2));

        return stock;
    }
}
