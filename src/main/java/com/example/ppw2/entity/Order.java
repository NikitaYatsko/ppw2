package com.example.ppw2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders", schema = "ppw2")
@Entity

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50, nullable = false)
    @NotNull(message = "Имя товара не может быть пустым")
    private String productName;
    @Column()
    @NotNull(message = "Количество товара должно быть хотя-бы 1")
    private Integer quantity;
    @CreationTimestamp
    private LocalDate orderDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}
