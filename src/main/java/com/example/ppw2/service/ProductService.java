package com.example.ppw2.service;

import com.example.ppw2.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer id);
    List<Product> getAllProducts();
}
