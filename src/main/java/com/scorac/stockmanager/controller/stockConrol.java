package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Stock;
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

    public stockConrol(StockRepository stockRepository, StockService stockService) {
        this.stockRepository = stockRepository;
        this.stockService = stockService;
    }

    @GetMapping("/total")
    public String stockContorl(Model model){
//        List<Product> products =stockService.listofALLSortedByName();
        LocalDate date = LocalDate.now();
        Stock stock= new Stock("ogorek", "kielbasa", 20, date);
        model.addAttribute("total", stock);
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

    @GetMapping("/datatable")
    @ResponseBody   //restcontrol
    public List<Stock> getForm(final @RequestParam Map<String, String> allRequestParams){
        ArrayList<Stock> stock = new ArrayList<>();

        LocalDate date = LocalDate.now();
        Stock prod1= new Stock("ogorek", "kielbasa", 20, date);
        Stock prod2= new Stock("sd", "kielbassda", 14, date);
//        model.addAttribute("total", stock);
        stock.add(prod1);
        stock.add(prod2);
        return stock;
//        return stockService.listofALLSortedByName();
    }
}
