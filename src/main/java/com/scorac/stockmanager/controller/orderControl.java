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
    @GetMapping("/orders")
    public String orders(){

        return "ordersList";
    }


    @GetMapping("/neworder")
    public String newOrders(Model model) {
//        Order order = new Order();
//        order.setOrderProducts(new ArrayList<>());
//
//        // Assume you fetch a list of products to display in the form
//        List<Product> availableProducts = productService.findAll();

//         Preparing an empty OrderProduct for each available product
//        for(Product product : availableProducts) {
//            OrderProduct op = new OrderProduct();
//            op.setProduct(product);
//            op.setQuantity(0); // Default quantity
//            order.getOrderProducts().add(op);
//        }

//        model.addAttribute("order", order);
//        model.addAttribute("availableProducts", availableProducts);

        return "ordersNew";
    }

//    @PostMapping("/submitOrder")
//    public String submitOrder(@ModelAttribute Order order) {
        // Handle saving the order and its orderProducts
//        return "neworder";
    }
//}

//}
