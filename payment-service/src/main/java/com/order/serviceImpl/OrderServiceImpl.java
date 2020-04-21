package com.order.serviceImpl;

import com.order.dao.OrderRepository;
import com.order.model.Order;
import com.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;
    @Override
    public Order creatOrder(Order order) {
        Order save = orderRepository.save(order);
        return save;
    }

    @Override
    public Order payOrder(Long id,Integer price) {
        Order order = findOrderById(id);
        if(order == null){
            return null;
        }
        if(order.getPrice() == price){
            order.setState(1);
            Order save = orderRepository.save(order);
            return save;
        }
        return null;
    }

    @Override
    public Order findOrderById(Long id) {
        Order order = null;
        try {
            order = orderRepository.findById(id).get();
        }catch (Exception e){
            System.out.println("order not exist");
        }
        return order;
    }
}
