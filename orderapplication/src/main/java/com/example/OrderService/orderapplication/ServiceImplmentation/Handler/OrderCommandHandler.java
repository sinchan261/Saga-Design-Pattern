package com.example.OrderService.orderapplication.ServiceImplmentation.Handler;

import com.example.CoreService.CoreApplication.Commands.ApproveOrderCommands;
import com.example.CoreService.CoreApplication.Commands.RejectOrderCommand;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@KafkaListener(topics={
        "${order.commands.topic.name}"
})
public class OrderCommandHandler {
    @Autowired
     private OrderServiceImple orderServiceImple;
    @KafkaHandler
    public void handlerCommand(ApproveOrderCommands approveOrderCommands){
orderServiceImple.approved( approveOrderCommands.getOrderId());
    }
    @KafkaHandler
    public void handleCommand(RejectOrderCommand rejectOrderCommand){
        orderServiceImple.rejectOrder( rejectOrderCommand.getOrderId());
    }
}
