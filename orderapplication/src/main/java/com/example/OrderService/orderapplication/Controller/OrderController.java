package com.example.OrderService.orderapplication.Controller;

import com.example.CoreService.CoreApplication.Dao.Order;
import com.example.OrderService.orderapplication.Dto.CreatedOrderRequest;
import com.example.OrderService.orderapplication.Dto.CreatedOrderResponse;
import com.example.OrderService.orderapplication.Dto.OrderHistory;
import com.example.OrderService.orderapplication.Dto.OrderHistoryResponse;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderHistoryServiceImpl;
import com.example.OrderService.orderapplication.ServiceImplmentation.OrderServiceImple;
import com.example.OrderService.orderapplication.ServiceInterface.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderServiceImple orderServiceImple;
    @Autowired
    ModelMapper modelMapper;
//    @Autowired
//    OrderHistoryServiceImpl orderServiceImpl;
    @PostMapping
    public CreatedOrderResponse placeorder(@RequestBody CreatedOrderRequest createdOrderRequest){
        Order order = new Order();
                order.setCustomerId(createdOrderRequest.getCustomerId());
        order.setProductId(createdOrderRequest.getProductId());
        order.setProductQuantity(createdOrderRequest.getProductQuantity());
        Order order1 = orderServiceImple.placeOrder(order);
      return   modelMapper.map(order1,CreatedOrderResponse.class);

    }

//    @GetMapping("/{orderId}/history")
//    @ResponseStatus(HttpStatus.ok)
//    public List<OrderHistoryResponse> getOrderHistory(@PathVariable UUID orderId){
//
//    }
}
