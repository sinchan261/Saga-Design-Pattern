package com.example.OrderService.orderapplication.entity;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private OrderStatus status;
    private UUID customerId;
    private Integer productQuantity;

}
