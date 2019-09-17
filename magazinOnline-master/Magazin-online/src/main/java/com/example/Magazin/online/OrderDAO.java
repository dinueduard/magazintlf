package com.example.Magazin.online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;

@Component
public class OrderDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer createNewOrder(Integer userId) {
        String sql = "insert into orders values (null,?);";
        Object[] params = new Object[1];
        params[0] = userId;
        jdbcTemplate.update(sql,params);
        Integer orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID() as id;", Integer.class);
        return orderId;
    }

    public void createNewOrderLine(Integer orderId,
                                   Integer productId,
                                   BigDecimal price,
                                   Integer quantity) {
        String sql = "insert into order_lines values (null,?,?,?,?)";
        Object[] params = new Object[4];
        params[0] = orderId;
        params[1] = price;
        params[2] = quantity;
        params[3] = productId;
        jdbcTemplate.update(sql,params);
    }
}
