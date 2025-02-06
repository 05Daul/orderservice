package com.example.orderservice.service;

import com.example.orderservice.dao.OrderDAO;
import com.example.orderservice.domain.*;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.kafka.CreateJsonProducer;
import com.example.orderservice.service.kafka.OrderStringProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j

public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper;
    private final OrderDAO orderDAO;
    private final CreateJsonProducer createJsonProducer;
    private final OrderStringProducer orderStringProducer;

    @Override
    @Transactional
    public void save(OrderRequestDTO orderdto) {
        log.info(orderdto.toString());
        List<OrderProductEntity> orderdetailentity =
                orderdto.getOrderproductlist()
                        .stream()
                        .map((detaildto)
                                -> {
                            return modelMapper.map(detaildto, OrderProductEntity.class);
                        })
                        .collect(Collectors.toList());
        log.info(orderdetailentity.toString());
        // 주문생성
        // 얄방향관게인 경우 부모테이블과 자식 테이블의 데이터를 한번에 저장할 수 있다.
        // 부모테이블에 레코드를 저장할 때, 자식 테이블의 레코드를 한 번에 저장할 수 있다.
        OrderEntity orderentity = OrderEntity.makeOrderEntity(orderdto.getAddr(), orderdto.getCustomerId(), orderdetailentity);
        orderDAO.save(orderentity);
//        for (int i = 0; i < orderdto.getOrderproductlist().size(); i++) {
//            createJsonProducer.send("dev.shop.order.create", orderdto.getOrderproductlist().get(i));
//        }
        for(OrderDetailDTO product : orderdto.getOrderproductlist()){
            orderStringProducer.sendMessage(product);
        }
//        OrderEntity orderEntity = modelMapper.map(orderdto, OrderEntity.class);
//        orderDAO.save(orderEntity);
    }

    @Override
    public OrderResponseDTO findById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> findAllByCustomerId(Long customerId) {
        return List.of();
    }
}
