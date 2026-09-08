package com.example.OrderService.orderapplication.Controller;

import com.example.CoreService.CoreApplication.Dao.Order;
import com.example.OrderService.orderapplication.Dto.CreatedOrderRequest;
import com.example.OrderService.orderapplication.Dto.CreatedOrderResponse;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderHistoryServiceImpl;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderServiceImple;
import com.example.OrderService.orderapplication.ServiceInterface.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImple orderServiceImple;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    OrderHistoryServiceImpl orderServiceImpl;
    @PostMapping
    public CreatedOrderResponse placeorder(@RequestBody CreatedOrderRequest createdOrderRequest){
        Order order = modelMapper.map(createdOrderRequest,Order.class);
        Order order1 = orderServiceImple.placeOrder(order);
      return   modelMapper.map(order1,CreatedOrderResponse.class);

    }
}
