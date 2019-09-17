package com.example.Magazin.online;

import java.util.List;

public interface ProductDAO {


    public List<Product> findAll();
    public List<Product> findAllByCatId(Integer catId);
    public Product findById(Integer productId);
}
