package com.example.CoreService.CoreApplication.Events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvents {
    private UUID orderId;
    private UUID customerId;
    private UUID productId;
    private Integer productQuantity;

}
