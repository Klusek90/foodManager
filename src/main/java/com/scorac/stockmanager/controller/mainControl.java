package com.scorac.stockmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainControl {

//    @CrossOrigin
    @GetMapping({"/","home","index"})
    public String homePage(){
        return "index";
    }

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

    @GetMapping ("/login")
    public String login(){
        return "login";
    }

    @GetMapping ("/logout")
    public String logout(){
        return "login";
    }
}
