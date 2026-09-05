package com.example.OrderService.orderapplication.Dto;

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
    private UUID status;
    private Timestamp createdAt;

}
