package com.scorac.stockmanager.service;

import com.scorac.stockmanager.model.Prep;
import com.scorac.stockmanager.model.Product;
import com.scorac.stockmanager.model.Recipe;
import com.scorac.stockmanager.model.RecipeProduct;
import com.scorac.stockmanager.model.TDO.ProductTDO;
import com.scorac.stockmanager.model.TDO.RecipeTDO;
import com.scorac.stockmanager.service.Repository.ProductRepository;
import com.scorac.stockmanager.service.Repository.RecipeProductRepository;
import com.scorac.stockmanager.service.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeProductService {
    @Autowired
    private RecipeProductRepository recipeProductRepository;
    private RecipeService recipeService;
    private ProductService productService;
    private  PrepService prepService;

    public RecipeProductService(RecipeProductRepository recipeProductRepository, RecipeService recipeService, ProductService productService, PrepService prepService) {
        this.recipeProductRepository = recipeProductRepository;
        this.recipeService = recipeService;
        this.productService = productService;
        this.prepService = prepService;
    }

    public void save(RecipeProduct recipeProduct){
        recipeProductRepository.save(recipeProduct);
    }

    public List<RecipeProduct> listforRecipe(Long id){
        return recipeProductRepository.findAllByRecipe_RecipeId(id);
    }

    public RecipeTDO fullRecipe(Long id){
        List<RecipeProduct> recipeProduct = listforRecipe(id);
        RecipeTDO recipeTDO= new RecipeTDO();
        List<ProductTDO> products= new ArrayList<>();

        for(int i =0; i < recipeProduct.size(); i++){
            ProductTDO productTDO = new ProductTDO();
            productTDO.setQuantity(recipeProduct.get(i).getQuantity());
            productTDO.setName(recipeProduct.get(i).getProduct().getName());
            products.add(productTDO);
        }
        recipeTDO.setName(recipeProduct.get(0).getRecipe().getName());
        recipeTDO.setProductList(products);
        return recipeTDO;
    }

    public void updatestock(Long recipieid, int times){
        List<RecipeProduct> recipeProduct = listforRecipe(recipieid);
        for(int i =0; i< recipeProduct.size(); i++){
            Product product=  recipeProduct.get(i).getProduct();
            Prep prep = prepService.findPrepWithProductId(product.getId());
            if(prep.getAmount() >0) {
                int updateAmount = prep.getAmount() - (recipeProduct.get(i).getQuantity() * times);
                if (updateAmount == 0) {
                    prepService.deletePrep(prep.getId());
                } else if( updateAmount < 0) {
                    prepService.deletePrep(prep.getId());
                    //convert from - to +
                    updateAmount =Math.abs(updateAmount);
                    prep = prepService.findPrepWithProductId(product.getId());
                    updateAmount = prep.getAmount()- updateAmount;

                    if(updateAmount > 0){
                        prep.setAmount(updateAmount);
                        prepService.save(prep);
                    }else {
                        prepService.deletePrep(prep.getId());
                    }

                }else{
                    prep.setAmount(updateAmount);
                    prepService.save(prep);
                }
            }
        }
    }
}
