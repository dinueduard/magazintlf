package com.example.Magazin.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartSession cartSession;

    @Autowired
    SecuritySession securitySession;

    @Autowired
    DatabaseProductDAO databaseProductDAO;

    @Autowired
    OrderDAO orderDAO;

    @GetMapping("add-to-cart")
    public String addToCart(
            @RequestParam("product_id") Integer productId) {
        List<Integer> ids = cartSession.getProductIds();

        Integer catId = databaseProductDAO.findById(productId).getCatId();

        ids.add(productId);
        cartSession.setProductIds(ids);

        return "redirect:/products?category_id=" + catId;
    }

    @GetMapping("send-order")
    public String sendOrder() {
        List<Integer> ids = cartSession.getProductIds();
        Integer orderId = orderDAO.createNewOrder(securitySession.getUserId());
        for(Integer id: ids) {
            orderDAO.createNewOrderLine(
                    orderId,
                    id,
                    databaseProductDAO.findById(id).price,
                    1
            );
        }
        cartSession.setProductIds(new ArrayList<>());

        return "redirect:/categories";
    }




}

