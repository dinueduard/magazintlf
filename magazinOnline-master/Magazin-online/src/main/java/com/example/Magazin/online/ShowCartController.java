package com.example.Magazin.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShowCartController {

    @Autowired
    CartSession cartSession;

    @Autowired
    DatabaseProductDAO databaseProductDAO;

    @Autowired
    DatabaseCategoryDAO databaseCategoryDAO;




    @GetMapping("/show-cart")
    public ModelAndView showCart(){

        ModelAndView modelAndView = new ModelAndView("show-cart");

        List<Integer> productIdsFromCart = cartSession.getProductIds();
        List<Product> productList = new ArrayList<>();
        for(Integer pId: productIdsFromCart) {
            Product p = databaseProductDAO.findById(pId);
            productList.add(p);
        }
        modelAndView.addObject("products",productList);
        return modelAndView;
    }
}
