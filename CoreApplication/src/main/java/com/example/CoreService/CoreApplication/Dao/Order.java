package com.example.CoreService.CoreApplication.Dao;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private Integer productQuantity;
    private OrderStatus status;
}
