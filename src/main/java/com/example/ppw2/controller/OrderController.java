package com.example.ppw2.controller;

import com.example.ppw2.entity.Order;
import com.example.ppw2.entity.dto.OrderResponseDTO;
import com.example.ppw2.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/orders")
@AllArgsConstructor

@RestController
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders() {
        log.debug("REST request to get all Orders");
        var response = orderService.getAllOrders();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Integer id) {
        log.info("Get Order with id {}", id);
        var response = orderService.getOrderById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody Order order) {
        log.info("creating new order {}", order);
        var response = orderService.createOrder(order);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> updateOrder(@RequestBody Order order, @PathVariable Integer id) {
        var response = orderService.updateOrder(order, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        log.info("deleting order with id {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}
