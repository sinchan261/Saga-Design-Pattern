package com.example.paymentservice.payment.Service;

import com.example.CoreService.CoreApplication.Dao.Payment;
import com.example.paymentservice.payment.dao.Jpa.Entity.PaymentEntity;
import com.example.paymentservice.payment.dao.Jpa.Repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PaymentServiceImple {
    public static final String SAMPLE_CREDIT_CARD_NUMBER = "374245455400126";

    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final CreditCardProcessorRemoteServiceImple ccpRemoteServiceimpl;

    public PaymentServiceImple(PaymentRepository paymentRepository, CreditCardProcessorRemoteServiceImple ccpRemoteServiceimpl) {
        this.paymentRepository = paymentRepository;
        this.ccpRemoteServiceimpl = ccpRemoteServiceimpl;
    }


    public Payment process(Payment payment){
        BigDecimal totalPrice = payment.getProductPrice().multiply(new BigDecimal(payment.getProductQuantity()));
        ccpRemoteServiceimpl.process(new BigInteger(SAMPLE_CREDIT_CARD_NUMBER),totalPrice);
        PaymentEntity paymentEntity = new PaymentEntity();
        BeanUtils.copyProperties(payment,paymentEntity);
        paymentRepository.save(paymentEntity);


        var processedPayment = new Payment();
        BeanUtils.copyProperties(payment, processedPayment);
        processedPayment.setId(paymentEntity.getId());
        return processedPayment;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll().stream().map(entity -> new Payment(entity.getId(), entity.getOrderId(), entity.getProductId(), entity.getProductPrice(), entity.getProductQuantity())
        ).collect(Collectors.toList());

    }
}
