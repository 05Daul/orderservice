package com.example.orderservice.dao;

import com.example.orderservice.domain.OrderEntity;

import java.util.List;

public interface OrderDAO {
    void save(OrderEntity orderEntity);
    OrderEntity findById(Long orderId);

    List<OrderEntity> getOrders(Long customerId);
}
