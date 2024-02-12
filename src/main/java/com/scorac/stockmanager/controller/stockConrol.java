package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.service.StockRepository;
import com.scorac.stockmanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class stockConrol {
    @Autowired
    private final StockRepository stockRepository;
    private final StockService stockService;

    public stockConrol(StockRepository stockRepository, StockService stockService) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    @GetMapping("/total")
    public String stockContorl(Model model){
        List<Product> products =stockService.listofALLSortedByName();
        model.addAttribute("total", products);
        return "stock";
    }

    @PostMapping("/stockupdate")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
//        stockService.updateProduct(productDTO);
        stockRepository.save(product);
//        List<Product> products = stockRepository.findAll();
        redirectAttributes.addFlashAttribute("successMessage", "Product successfully updated!");
        return "redirect:/stock/total"; // Redirect to an appropriate view
    }
}
