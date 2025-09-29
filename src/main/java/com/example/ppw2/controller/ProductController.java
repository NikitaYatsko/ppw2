package com.example.ppw2.controller;

import com.example.ppw2.entity.Product;
import com.example.ppw2.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {

  private final ProductService productService;
    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        log.info("get product by id {}", id);
        return Optional.ofNullable(productService.getProductById(id));
    }
    @GetMapping
    public List<Product> getAllProducts() {
        log.info("get all products");
        return productService.getAllProducts();
    }

}
