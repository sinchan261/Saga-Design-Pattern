package com.example.OrderService.orderapplication.ServiceImplmentation;

import com.example.CoreService.CoreApplication.Dao.Order;
import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.OrderService.orderapplication.Repository.OrderRepository;
import com.example.OrderService.orderapplication.ServiceInterface.OrderService;
import com.example.OrderService.orderapplication.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OrderServiceImple implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Order placeOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order,OrderEntity.class);
        Order order1 = modelMapper.map(orderEntity,Order.class);
        return  order1;
    }
}
//private UUID orderId;
//private UUID customerId;
//private UUID productId;
//private Integer productQuantity;
//private OrderStatus status;


//private UUID id;
//private OrderStatus status;
//private UUID customerId;
//private Integer productQuantity;
