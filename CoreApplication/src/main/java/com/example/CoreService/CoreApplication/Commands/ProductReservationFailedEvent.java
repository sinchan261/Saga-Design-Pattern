package com.example.CoreService.CoreApplication.Commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReservationFailedEvent {

    private UUID productId;
    private UUID orderId;
    private Integer productQuantity;

}
