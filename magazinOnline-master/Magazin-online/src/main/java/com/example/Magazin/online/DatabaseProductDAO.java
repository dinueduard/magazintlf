package com.example.Magazin.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseProductDAO implements ProductDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", new ProductMapper());
    }

    @Override
    public List<Product> findAllByCatId(Integer catId) {
        return jdbcTemplate.query("select * from product where category_id="+ catId, new ProductMapper());
    }


    @Override
    public Product findById(Integer prodId) {
        return jdbcTemplate.query("select * from product where id="+ prodId, new ProductMapper()).get(0);
    }


}