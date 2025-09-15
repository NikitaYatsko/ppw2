package com.example.ppw2.service;

import com.example.ppw2.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Integer id);

    Order updateOrder(Order order, Integer id);

    Order createOrder(Order order);

    void deleteOrder(Integer id);

}
