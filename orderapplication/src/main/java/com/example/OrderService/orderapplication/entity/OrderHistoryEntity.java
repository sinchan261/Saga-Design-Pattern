package com.example.OrderService.orderapplication.entity;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;
@Table(name = "orders_history")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private UUID orderId;
    private Timestamp createdAt;

}
