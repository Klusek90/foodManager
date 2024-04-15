package com.scorac.stockmanager.controller;


import com.scorac.stockmanager.model.Entity.Prep;
import com.scorac.stockmanager.model.Entity.Product;
import com.scorac.stockmanager.model.TDO.SaleDTO;
import com.scorac.stockmanager.service.MadeService;
import com.scorac.stockmanager.service.PrepService;
import com.scorac.stockmanager.service.ProductService;
import com.scorac.stockmanager.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class prepControl {
    @Autowired
    private ProductService productService;
    private PrepService prepService;
    private SaleService saleService;
    private MadeService madeService;

    public prepControl(ProductService productService, PrepService prepService, SaleService saleService, MadeService madeService) {
        this.productService = productService;
        this.prepService = prepService;
        this.saleService = saleService;
        this.madeService = madeService;
    }

    @GetMapping("/prep")
    public String newPrep(Model model) {
        List<String> productNames = productService.productSearch();
        model.addAttribute("itemsName", productNames);

        return "prepNew";
    }

    @PostMapping("/addPrep")
    public String submitOrder(@ModelAttribute Prep prep , @RequestParam(value = "productIds") List<Long> productIds, @RequestParam(value = "quantities") List<Integer> quantities, RedirectAttributes redirectAttributes) {
        // Retrieve products by their IDs
        List<Product> products = productService.getProductsByIds(productIds);
        LocalDate timestamp = LocalDate.now();
        // Save recipe products
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Integer amount = quantities.get(i);
            Prep prepared =  new Prep();
            prepared.setAmount(amount);
            prepared.setProductionDate(timestamp);
            prepared.setExpireDate(timestamp.plusDays(product.getLifeLength()));
            prepared.setProduct(product);
            prepService.save(prepared);
            madeService.save(prepared);
        }
        redirectAttributes.addFlashAttribute("message", "Product preparation status saved");
        return "redirect:/prep";
    }

    @GetMapping("/sale")
    public String addSale() {
        return "sale";
    }
    @PostMapping("/addsale")
    public String addSale(SaleDTO sale, RedirectAttributes redirectAttributes) {
        String respond=  saleService.save(sale);
        if(respond.contains("Faild")){
            redirectAttributes.addFlashAttribute("error", respond);
        }else{
            redirectAttributes.addFlashAttribute("message", respond);
        }

        return "redirect:/sale";
    }
}