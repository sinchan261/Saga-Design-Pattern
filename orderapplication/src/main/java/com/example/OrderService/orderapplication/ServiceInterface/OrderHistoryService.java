package com.example.OrderService.orderapplication.ServiceInterface;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.OrderService.orderapplication.Dto.OrderHistory;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryService {
    void add(UUID orderId, OrderStatus orderStatus);
    List<OrderHistory> findByOrderId(UUID id);
}
