package com.example.OrderService.orderapplication.Dto;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class CreatedOrderResponse {
    @NonNull
    private UUID customerId;
    @NonNull
    private UUID productId;
    @NonNull
    private UUID id;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Integer productQuantity;

}
