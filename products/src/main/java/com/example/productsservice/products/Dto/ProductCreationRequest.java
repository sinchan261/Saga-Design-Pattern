package com.example.productsservice.products.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationRequest {
    private String name;
    @NotNull
    private BigDecimal price;
    private Integer quantity;

}
