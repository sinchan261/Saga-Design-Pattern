package com.example.OrderService.orderapplication.ServiceImplmentation;

import com.example.CoreService.CoreApplication.Dao.Order;
import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.CoreService.CoreApplication.Events.OrderAppprovedEvents;
import com.example.CoreService.CoreApplication.Events.OrderCreatedEvents;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
@Slf4j
@Service
public class OrderServiceImple implements OrderService {
    @Autowired
    Environment environment;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    ModelMapper modelMapper;
    @Value("${orders.events.topic.name}")
    private  String ordersEvents2;

    @Override
//    public class Order {
//        private UUID id;
//        private UUID customerId;
//        private UUID productId;
//        private Integer productQuantity;
//        private OrderStatus status;
//    }

    public Order placeOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order,OrderEntity.class);
        orderEntity.setStatus(OrderStatus.CREATED);
        log.info("log of order entity is {}",orderEntity.getCustomerId());


// store the orders into database
        orderRepository.save(orderEntity);

//        publish a event for oreder created
        OrderCreatedEvents placeholder = new OrderCreatedEvents(orderEntity.getId(),
orderEntity.getCustomerId(),orderEntity.getProductId(),orderEntity.getProductQuantity());

        kafkaTemplate.send(environment.getProperty("orders.events.topic.name"),placeholder);



        Order order1 = modelMapper.map(orderEntity,Order.class);
        return  order1;
    }

    public void approved(UUID orderId) {
       OrderEntity orderEntity= orderRepository.findById(orderId).orElse(null);
         orderEntity.setStatus(OrderStatus.APPROVED);
        OrderAppprovedEvents orderAppprovedEvents = new OrderAppprovedEvents();
           orderRepository.save(orderEntity);
        orderAppprovedEvents.setOrderId(orderId);
        kafkaTemplate.send(ordersEvents2,orderAppprovedEvents);
    }

    public void rejectOrder(UUID orderId) {
        OrderEntity orderEntity= orderRepository.findById(orderId).orElse(null);
       orderEntity.setStatus(OrderStatus.REJECTED);
       orderRepository.save(orderEntity);
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
