package com.example.OrderService.orderapplication.Dto;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class OrderHistoryResponse {
    private UUID id;
    private UUID orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Timestamp createdAt;

}
