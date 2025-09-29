package com.example.ppw2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders", schema = "ppw2")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull()
    private Integer quantity;
    @CreationTimestamp
    private LocalDate orderDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
