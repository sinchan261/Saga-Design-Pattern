package com.example.CoreService.CoreApplication.Dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    private UUID id;
    private UUID orderId;
    private UUID paymentId;

}
