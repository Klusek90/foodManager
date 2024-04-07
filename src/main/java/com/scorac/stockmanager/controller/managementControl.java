package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.service.ProductService;
import com.scorac.stockmanager.service.RecipeProductService;
import com.scorac.stockmanager.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class managementControl {
    @Autowired
    private ProductService productService;
    private RecipeService recipeService;

    private RecipeProductService recipeProductService;


    public managementControl(ProductService productService, RecipeService recipeService, RecipeProductService recipeProductService) {
        this.productService = productService;
        this.recipeService = recipeService;
        this.recipeProductService = recipeProductService;
    }


    @GetMapping("/recipes")
    public String recipes(Model model){
        List<Recipe> recipe= recipeService.getAll();
        model.addAttribute("recipe", recipe);
        return "recipes";
    }


    @GetMapping("/newproduct")
    public String newProduct(Model model){
        List<String> productList= productService.productSearch();
        model.addAttribute("itemsName", productList);
        return "recipesNewProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        String respond =productService.save(product);
        redirectAttributes.addFlashAttribute("message", respond);
        return "redirect:/newproduct"; // Redirect to prevent duplicate submissions
    }

    @GetMapping("/newrecipe")
    public String addRecipes(Model model){
//        List<String> productList= productService.productSearch();
        List<Product> whole = productService.getAllIngredients();
        List<String> productNames= productService.productSearch();
        model.addAttribute("itemsName", productNames);
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("products", whole);
        return "recipesAdd";
    }
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe recipe, @RequestParam(value = "productIds") List<Long> productIds, @RequestParam(value = "quantities") List<Integer> quantities, RedirectAttributes redirectAttributes) {
        // Retrieve products by their IDs
        List<Product> products = productService.getProductsByIds(productIds);

        // Save recipe first
        recipeService.save(recipe);

        // Save recipe products
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Integer quantity = quantities.get(i);
            RecipeProduct recipeProduct = new RecipeProduct();
            recipeProduct.setProduct(product);
            recipeProduct.setQuantity(quantity);
            recipeProduct.setRecipe(recipe); // Set the recipe for the recipe product
            recipeProductService.save(recipeProduct);
        }

        redirectAttributes.addFlashAttribute("message", "New recipe added");
        return "redirect:/newrecipe";
    }
}
