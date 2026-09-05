package com.example.OrderService.orderapplication.Repository;

import com.example.OrderService.orderapplication.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
