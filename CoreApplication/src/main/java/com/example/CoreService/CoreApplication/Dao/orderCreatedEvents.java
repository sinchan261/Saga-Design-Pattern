package com.example.CoreService.CoreApplication.Dao;

import lombok.*;

import java.util.UUID;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class orderCreatedEvents {
        public UUID orderId;
        private UUID customerId;
        private UUID productId;
        private UUID productQuantity;
    }


