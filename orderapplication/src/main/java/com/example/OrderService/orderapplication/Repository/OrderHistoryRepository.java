package com.example.OrderService.orderapplication.Repository;

import com.example.OrderService.orderapplication.entity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderHistoryRepository  extends JpaRepository<OrderHistoryEntity, UUID> {
    List<OrderHistoryEntity> findByOrderId(UUID orderId);
}
