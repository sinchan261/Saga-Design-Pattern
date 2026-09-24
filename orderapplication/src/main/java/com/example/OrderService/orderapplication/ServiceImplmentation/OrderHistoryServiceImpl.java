package com.example.OrderService.orderapplication.ServiceImplmentation;

import com.example.CoreService.CoreApplication.Enumes.OrderStatus;
import com.example.OrderService.orderapplication.Dto.OrderHistory;
import com.example.OrderService.orderapplication.Repository.OrderHistoryRepository;
import com.example.OrderService.orderapplication.entity.OrderHistoryEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderHistoryServiceImpl {
    @Autowired
    private    OrderHistoryRepository orderHistoryRepository;
//    @Autowired
//
     public  void add(UUID orderId, OrderStatus orderStatus){
         OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
         orderHistoryEntity.setOrderId(orderId);
         orderHistoryEntity.setStatus(orderStatus);
         orderHistoryEntity.setCreatedAt(new Timestamp(new Date().getTime()));

     }
//     it returns the list of order using orderId
     public List<OrderHistory> findByOrderId(UUID orderId){
         var entites= orderHistoryRepository.findByOrderId(orderId);
         return entites.stream().map(entity->{
             OrderHistory orderHistory = new OrderHistory();
             BeanUtils.copyProperties(entity,orderHistory);
             return orderHistory;
         }).toList();
     }
}
