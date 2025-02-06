package com.example.orderservice.repository;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.domain.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository
                        extends JpaRepository<OrderProductEntity,Long> {
}
