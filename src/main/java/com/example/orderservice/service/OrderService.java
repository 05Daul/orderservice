package com.example.orderservice.service;

import com.example.orderservice.domain.OrderRequestDTO;
import com.example.orderservice.domain.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    void save(OrderRequestDTO orderdto);
    OrderResponseDTO findById(Long orderId);

    List<OrderResponseDTO> findAllByCustomerId(Long customerId);
}
