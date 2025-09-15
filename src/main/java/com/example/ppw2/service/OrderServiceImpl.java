package com.example.ppw2.service;

import com.example.ppw2.entity.Order;
import com.example.ppw2.exception.OrderNotFoundException;
import com.example.ppw2.repository.OrderRepository;
import com.example.ppw2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;


    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order not found"));

    }

    @Override
    @Transactional
    public Order updateOrder(Order order, Integer id) {
        Order updatedOrder = orderRepository.findById(id).orElseThrow(
                () -> new OrderNotFoundException("Order not found")
        );
        updatedOrder.setProductName(order.getProductName());
        updatedOrder.setQuantity(order.getQuantity());
        return orderRepository.save(updatedOrder);
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}
