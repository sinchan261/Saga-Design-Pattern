package com.example.paymentservice.payment.Service.Handler;

import com.example.CoreService.CoreApplication.Commands.ProcessPaymentCommand;
import com.example.CoreService.CoreApplication.Dao.Payment;
import com.example.CoreService.CoreApplication.Events.PaymentFailEvent;
import com.example.CoreService.CoreApplication.Events.PaymentProcessEvents;
import com.example.CoreService.CoreApplication.Exceptions.CreditCardProcessorUnavailableException;
import com.example.paymentservice.payment.Service.PaymentServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@KafkaListener(topics={"${payment.commands.topic.name}"})
public class PaymentCommandsHandler {

    @Autowired
    PaymentServiceImple paymentServiceImple;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Value("${payment.events.topic.name}")
    private  String PaymentEventsName;



    @KafkaHandler
    public void handleCommand( ProcessPaymentCommand processPaymentCommand){
       try {
            Payment payment = new Payment(
                    processPaymentCommand.getOrderId(),
                    processPaymentCommand.getProductId(),
                    processPaymentCommand.getProductPrice(),
                    processPaymentCommand.getProductQuantity()
            );
            Payment processpayemnt = paymentServiceImple.process(payment);
            log.info("payment is {} in line no 44{}",processpayemnt);
           PaymentProcessEvents paymentProcessEvents = new PaymentProcessEvents(processpayemnt.getOrderId(),processpayemnt.getId());

          kafkaTemplate.send(PaymentEventsName,paymentProcessEvents);

        } catch (CreditCardProcessorUnavailableException e) {
           log.error(e.getLocalizedMessage(),e);
           PaymentFailEvent paymentFailEvent =new PaymentFailEvent();
           paymentFailEvent.setOrderId(processPaymentCommand.getOrderId());
           paymentFailEvent.setProductId(processPaymentCommand.getProductId());
           paymentFailEvent.setProductQuantity(processPaymentCommand.getProductQuantity());
           kafkaTemplate.send(PaymentEventsName,paymentFailEvent);
       }
    }

    @KafkaHandler
    public void handleCommand(@Payload PaymentFailEvent processPaymentCommand) {
    }
}
