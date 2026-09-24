package com.example.CoreService.CoreApplication.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

public class ProductInsufficientQuantityException extends RuntimeException{
    private final UUID productId;
    private final UUID orderId;

}
