package com.example.orderservice.controller;

//import com.example.orderservice.domain.OrderDetailDTO;

import com.example.orderservice.domain.OrderRequestDTO;
import com.example.orderservice.domain.OrderResponseDTO;
import com.example.orderservice.service.OrderService;
//import com.example.orderservice.service.OrderStringProducer;
import com.example.orderservice.service.kafka.CreateJsonProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
@Slf4j
public class OrderController {
    //OrderRequestDTO 는 주문에 대한 일반적인 내용과 주문하는 상품에 대한 정보를 담을 수 있도록 구성
    private final OrderService orderService;

    @PostMapping("/create")
    public void create(@RequestBody OrderRequestDTO order) {
        orderService.save(order);


    }

    @GetMapping("/getOrders/{customerId}")
    public List<OrderResponseDTO> getOrders(@PathVariable Long customerId) {
        return null;
    }
}

