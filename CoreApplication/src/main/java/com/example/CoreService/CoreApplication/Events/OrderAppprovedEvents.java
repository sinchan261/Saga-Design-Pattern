package com.example.CoreService.CoreApplication.Events;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderAppprovedEvents {
    private UUID orderId;
}
