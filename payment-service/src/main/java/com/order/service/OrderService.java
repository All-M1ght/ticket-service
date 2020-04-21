package com.order.service;

import com.order.model.Order;

public interface OrderService {
    Order creatOrder(Order order);
    Order payOrder(Long id,Integer price);
    Order findOrderById(Long id);
}
