package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mainControl {
    @Autowired
    private final StockService stockService;

    public mainControl(StockService stockService) {
        this.stockService = stockService;
    }
    // TODO: 10/02/2024 Add AI and ML algorithm  !!!
    // TODO: 12/02/2024 Add Pictures for product, users and recopies
    // TODO: 13/02/2024 proper render the card size
    // TODO: 14/02/2024 create report
    
    @GetMapping({"/","home","index"})
    public String homePage(){
        return "index";
    }

    @GetMapping("/recipes")
    public String recipes(Model model){
        List<String> productNames= stockService.productSearch();
        model.addAttribute("itemsName", productNames);
        return "recipes";
    }

    @GetMapping("/solution")
    public String queryChat(){
        return "queryGPT";
    }

    @GetMapping("/orders")
    public String orders(){
        return "orders";
    }

    @GetMapping("/reports")
    public String reports(){
        return "reports";
    }

    @GetMapping("/calendar")
    public String calendar(){
        return "calendar";
    }

    @GetMapping("/setup")
    public String setup(){
        return "setup";
    }

    @GetMapping("/wastage")
    public String wastage(Model model){
        List<String> productNames= stockService.productSearch();
        model.addAttribute("itemsName", productNames);
        return "wastage";
    }


    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping ("/logout")
    public String logout(){
        return "login";
    }
}
