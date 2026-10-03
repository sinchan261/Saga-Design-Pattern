package com.example.paymentservice.payment.Service.Handler;

import com.example.CoreService.CoreApplication.Commands.ProcessPaymentCommand;
import com.example.CoreService.CoreApplication.Dao.Payment;
import com.example.CoreService.CoreApplication.Exceptions.CreditCardProcessorUnavailableException;
import com.example.paymentservice.payment.Service.PaymentServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@KafkaListener(topics={"${payment.commands.topic.name}"})
public class PaymentCommandsHandler {

    @Autowired
    PaymentServiceImple paymentServiceImple;
    @KafkaHandler
    public void handleCommand(@Payload ProcessPaymentCommand processPaymentCommand){
       try {
            Payment payment = new Payment(
                    processPaymentCommand.getOrderId(),
                    processPaymentCommand.getProductId(),
                    processPaymentCommand.getProductPrice(),
                    processPaymentCommand.getProductQuantity()
            );
            Payment processpayemnt = paymentServiceImple.process(payment);
        } catch (CreditCardProcessorUnavailableException e) {
           log.error(e.getLocalizedMessage(),e);
       }
    }

}
