package com.example.OrderService.orderapplication.Dto;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory {
    private UUID id;
    private UUID orderId;
    private OrderStatus orderStatus;
    private Timestamp createdAt;
}
