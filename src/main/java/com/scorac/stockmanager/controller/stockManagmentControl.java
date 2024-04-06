package com.scorac.stockmanager.controller;

import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.service.ProductRepository;
import com.scorac.stockmanager.service.ProductService;
import com.scorac.stockmanager.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;

@Controller
public class stockManagmentControl {

    private ProductService productService;
    private RecipeService recipeService;

    public stockManagmentControl(ProductService productService, RecipeService recipeService) {
        this.productService = productService;
        this.recipeService = recipeService;
    }


    @GetMapping("/recipes")
    public String recipes(Model model){
//        List<String> productNames= stockService.productSearch();
//        model.addAttribute("itemsName", productNames);
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
        List<String> productList= productService.productSearch();
        List<Product> whole = productService.getAllIngredients();
//        model.addAttribute("product", productList);
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("products", whole);
        return "recipesAdd";
    }
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe recipe, @RequestParam("productIds") List<Long> productIds, @RequestParam("quantities") List<Integer> quantities, RedirectAttributes redirectAttributes) {
        // Retrieve products by their IDs
        List<Product> products = productService.getProductsByIds(productIds);

        // Create RecipeProduct instances and associate them with the recipe
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Integer quantity = quantities.get(i);
            RecipeProduct recipeProduct = new RecipeProduct();
            recipeProduct.setProduct(product);
            recipeProduct.setQuantity(quantity);
            recipeProduct.setRecipe(recipe); // Associate the recipe with the RecipeProduct
        }

        recipeService.save(recipe);
        redirectAttributes.addFlashAttribute("message", "New recipe added");
        return "redirect:/newrecipe";
    }
}
