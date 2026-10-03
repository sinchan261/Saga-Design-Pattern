package com.example.OrderService.orderapplication.Saga;

//The primary purpose of this class will be coordinate a series
// of related transactions across multiple microservices

//it will act as a central orchestrator , making sure that all steps in our complex business provcess are
//in the correct order


import com.example.CoreService.CoreApplication.Commands.ProcessPaymentCommand;
import com.example.CoreService.CoreApplication.Commands.ProductReservedEvent;
import com.example.CoreService.CoreApplication.Commands.ReserveProductCommand;
import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.CoreService.CoreApplication.Events.OrderCreatedEvents;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderHistoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@KafkaListener(topics= {"${orders.events.topic.name}",
        "${products.events.topic.name}"
})

public class OrderSaga {

    private final OrderHistoryServiceImpl orderHistoryService;
    @Value("${products.commands.topic.name}")
    private  String productCommandsTopicName;
    @Value("${payment.commands.topic.name}")
    private String paymentCommandTopicName;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    public OrderSaga(@Autowired OrderHistoryServiceImpl orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @KafkaHandler
public void handleEvent(OrderCreatedEvents orderCreatedEvents){
//        public class OrderCreatedEvents
//            private UUID orderId;
//            private UUID customerId;
//            private UUID productId;
//            private Integer productQuantity;

  ReserveProductCommand reservedProductCommand = new ReserveProductCommand(
          orderCreatedEvents.getProductId(), orderCreatedEvents.getProductQuantity(), orderCreatedEvents.getOrderId()
);
    kafkaTemplate.send(productCommandsTopicName,reservedProductCommand);
    orderHistoryService.add(orderCreatedEvents.getOrderId(), OrderStatus.CREATED);

    }

    @KafkaHandler
    public void handleEvent(ProductReservedEvent productReservedEvent){
        ProcessPaymentCommand processPaymentCommand = new ProcessPaymentCommand
                (productReservedEvent.getOrderId(),productReservedEvent.getProductId(),
                        productReservedEvent.getProductPrice(), productReservedEvent.getProductQuantity());

        kafkaTemplate.send(paymentCommandTopicName,processPaymentCommand);
    }
}
