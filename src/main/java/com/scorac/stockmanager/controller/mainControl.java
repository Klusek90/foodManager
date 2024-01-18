package com.scorac.stockmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainControl {

    @GetMapping({"/","home","index"})
    public String homePage(){
        return "index";
    }

//    @GetMapping("/users")
//    public String manageUsers(){
//        return "users";
//    }
//    @GetMapping("/stock")
//    public String stockContorl(){
//        return "stock";
//    }

    @GetMapping("/recipes")
    public String recipes(){
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
}
