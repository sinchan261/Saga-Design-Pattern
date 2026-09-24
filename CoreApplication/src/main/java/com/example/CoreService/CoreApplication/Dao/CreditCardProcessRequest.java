package com.example.CoreService.CoreApplication.Dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardProcessRequest {
@NotNull
private BigInteger creditCardNumber;
@NotNull
private BigDecimal paymentAmount;

}
