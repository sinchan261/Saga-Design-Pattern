package com.example.OrderService.orderapplication.entity;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;
@Table(name = "orders_history")
@Entity
public class OrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private OrderStatus status;
    private UUID orderId;
    private Timestamp createdAt;

}
