package com.example.OrderService.orderapplication.ServiceInterface;

import com.example.CoreService.CoreApplication.Dao.Order;

public interface OrderService {
    Order placeOrder(Order order);
}
