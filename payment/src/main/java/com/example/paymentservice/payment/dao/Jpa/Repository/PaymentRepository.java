package com.example.paymentservice.payment.dao.Jpa.Repository;

import com.example.paymentservice.payment.dao.Jpa.Entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {

}
