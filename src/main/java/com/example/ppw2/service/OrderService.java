package com.example.ppw2.service;

import com.example.ppw2.entity.Order;
import com.example.ppw2.entity.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO getOrderById(Integer id);

    OrderResponseDTO updateOrder(Order order, Integer id);

    OrderResponseDTO createOrder(Order order);

    void deleteOrder(Integer id);

}
