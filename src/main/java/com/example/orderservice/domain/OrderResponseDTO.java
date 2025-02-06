package com.example.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Long orderId;
    private Date orderDate;//주문한시간
    private String addr; //배송주소
    private Long customerId;
    private List<OrderDetailDTO> orderproductlist;


}
