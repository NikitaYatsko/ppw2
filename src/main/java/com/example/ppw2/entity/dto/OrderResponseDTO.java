package com.example.ppw2.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderResponseDTO {
    private Integer id;
    private Integer quantity;
    private LocalDate orderDate;
    private Integer userId;
    private String username;
    private Integer productId;
    private String productTitle;
}
