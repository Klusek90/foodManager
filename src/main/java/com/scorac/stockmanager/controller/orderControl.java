package com.scorac.stockmanager.controller;

//import com.scorac.stockmanager.model.Order;
//import com.scorac.stockmanager.model.OrderProduct;
import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class orderControl {
    @Autowired
    private ProductService productService;
    @GetMapping("/prep")
    public String preparation(){

        return "prepList";
    }


    @GetMapping("/newprep")
    public String newPrep(Model model) {
        List<String> productNames= productService.productSearch();
        model.addAttribute("itemsName", productNames);

        return "prepNew";
    }

//    @PostMapping("/submitOrder")
//    public String submitOrder(@ModelAttribute Order order) {
        // Handle saving the order and its orderProducts
//        return "neworder";
    }
//}

//}
