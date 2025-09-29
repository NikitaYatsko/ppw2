package com.example.ppw2.entity.dto;

import com.example.ppw2.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserResponseDTO implements Serializable {
    private Integer id;
    private String email;
    private Integer age;
    private String username;
    private List<Order> orders;
}
