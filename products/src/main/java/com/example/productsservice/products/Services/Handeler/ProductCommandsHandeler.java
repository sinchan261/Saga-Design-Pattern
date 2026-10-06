package com.example.productsservice.products.Services.Handeler;

import com.example.CoreService.CoreApplication.Commands.*;
import com.example.CoreService.CoreApplication.Dao.Product;
import com.example.productsservice.products.Services.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@KafkaListener(topics={"${products.commands.topic.name}"})
public class ProductCommandsHandeler {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final String productEventsTopicName;
    public ProductCommandsHandeler(KafkaTemplate<String, Object> kafkaTemplate,@Value("${product.events.topic.name}") String productEventsTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.productEventsTopicName = productEventsTopicName;
    }

    @KafkaHandler
    public void handeleCommand(ReserveProductCommand reserveProductCommand){
     try{
         Product desiredProduct = new Product(reserveProductCommand.getProductId(), reserveProductCommand.getProductQuantity());
         productService.reserve(desiredProduct, reserveProductCommand.getOrderId());
         ProductReservedEvent productReservedEvent = new ProductReservedEvent();

         productReservedEvent.setOrderId(reserveProductCommand.getOrderId());
         productReservedEvent.setProductId(reserveProductCommand.getProductId());
         productReservedEvent.setProductPrice(BigDecimal.valueOf(reserveProductCommand.getProductQuantity()));
         productReservedEvent.setProductQuantity(reserveProductCommand.getProductQuantity());

         kafkaTemplate.send(productEventsTopicName,productReservedEvent);
     }catch(Exception e)  {
         log.info("ProductReservationFailed{}",e.getMessage());


         ProductReservationFailedEvent productReservationFailedEvent=
                 new ProductReservationFailedEvent(reserveProductCommand.getProductId(),
                         reserveProductCommand.getOrderId(),reserveProductCommand.getProductQuantity());
         kafkaTemplate.send(productEventsTopicName,productReservationFailedEvent);

     }
 }

 @KafkaHandler
    public void handleCommand(CancelProductReservtionCommand command){
        Product productToCancel = new Product(
             command.getProductId(),
             command.getProductQuantity()
        );
        productService.cancelReservation(productToCancel,command.getOrderId());

     ProductReservationCancelledEvent productReservationCancelledEvent = new
             ProductReservationCancelledEvent(command.getProductId(),command.getOrderId());
     kafkaTemplate.send(productEventsTopicName,productReservationCancelledEvent);
 }


}
