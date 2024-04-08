package com.scorac.stockmanager.controller;

//import com.scorac.stockmanager.model.Order;
//import com.scorac.stockmanager.model.OrderProduct;
import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.service.PrepService;
import com.scorac.stockmanager.service.ProductService;
import com.scorac.stockmanager.service.Repository.PrepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class prepControl {
    @Autowired
    private ProductService productService;
    private PrepService prepService;

    public prepControl(ProductService productService, PrepService prepService) {
        this.productService = productService;
        this.prepService = prepService;
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
            Prep prepProduct =  new Prep();
            prepProduct.setProductid(product.getId());
            prepProduct.setAmount(amount);
            prepProduct.setProductionDate(timestamp);
            prepProduct.setExpireDate(timestamp.plusDays(product.getLifeLength()));
            prepService.save(prepProduct);
        }

        redirectAttributes.addFlashAttribute("message", "Product preparation status saved");
        return "redirect:/prep";
    }
}