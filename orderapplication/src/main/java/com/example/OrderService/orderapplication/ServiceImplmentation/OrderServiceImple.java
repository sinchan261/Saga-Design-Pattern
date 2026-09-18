package com.example.OrderService.orderapplication.ServiceImplmentation;

import com.example.CoreService.CoreApplication.Dao.Order;
import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.OrderService.orderapplication.Repository.OrderRepository;
import com.example.OrderService.orderapplication.ServiceInterface.OrderService;
import com.example.OrderService.orderapplication.entity.OrderEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
public class OrderServiceImple implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Order placeOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order,OrderEntity.class);
        log.info("log of order entity is {}",orderEntity.getCustomerId());
        orderRepository.save(orderEntity);
        Order order1 = modelMapper.map(orderEntity,Order.class);
        return  order1;
    }
}
//public class OrderEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//    private OrderStatus status;
//    private UUID customerId;
//    private Integer productQuantity;
//    private UUID productId;
//}
//public class Order {
//    private UUID id;
//    private UUID customerId;
//    private UUID productId;
//    private Integer productQuantity;
//    private OrderStatus status;
//}

//private UUID orderId;
//private UUID customerId;
//private UUID productId;
//private Integer productQuantity;
//private OrderStatus status;


//private UUID id;
//private OrderStatus status;
//private UUID customerId;
//private Integer productQuantity;
