package com.example.orderservice.dao;

import com.example.orderservice.domain.OrderEntity;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderDAOImpl implements OrderDAO{
    private final OrderRepository repository;
    @Override
    public void save(OrderEntity orderEntity) {
        repository.save(orderEntity);
    }

    @Override
    public OrderEntity findById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderEntity> getOrders(Long customerId) {
        List<OrderEntity> orderlist =  repository.findByCustomerId(customerId);
        log.info("조회결과=>{}",orderlist);
        log.info("상세조회결과=>{}",orderlist.get(0).getOrderproductlist());
        return orderlist;
    }
}
