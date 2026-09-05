package com.example.OrderService.orderapplication.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.NonNull;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedOrderRequest {
    @NonNull
    private UUID customerId;
    @NonNull
    private UUID productId;

    private Integer productQuantity;

}
