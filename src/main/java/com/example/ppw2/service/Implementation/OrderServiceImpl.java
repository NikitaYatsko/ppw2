package com.example.ppw2.service.Implementation;

import com.example.ppw2.entity.Order;
import com.example.ppw2.entity.dto.OrderResponseDTO;
import com.example.ppw2.exception.OrderNotFoundException;
import com.example.ppw2.exception.ProductNotFoundException;
import com.example.ppw2.exception.UserNotFoundException;
import com.example.ppw2.repository.OrderRepository;
import com.example.ppw2.repository.ProductRepository;
import com.example.ppw2.repository.UserRepository;
import com.example.ppw2.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // ----------------- DTO Mapping -----------------
    private OrderResponseDTO mapToDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getQuantity(),
                order.getOrderDate(),
                order.getUser().getId(),
                order.getUser().getUsername(),
                order.getProduct().getId(),
                order.getProduct().getTitle()
        );
    }

    // ----------------- GET ALL -----------------
    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ----------------- GET BY ID -----------------
    @Override
    public OrderResponseDTO getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return mapToDTO(order);
    }

    // ----------------- CREATE -----------------
    @Override
    @Transactional
    public OrderResponseDTO createOrder(Order order) {
        var user = userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        var product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        order.setUser(user);
        order.setProduct(product);

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    // ----------------- UPDATE -----------------
    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Order orderData, Integer orderId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        existingOrder.setQuantity(orderData.getQuantity());

        Order updatedOrder = orderRepository.save(existingOrder);
        return mapToDTO(updatedOrder);
    }

    // ----------------- DELETE -----------------
    @Override
    @Transactional
    public void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        orderRepository.delete(order);
    }
}
