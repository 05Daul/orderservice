package com.example.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private Date orderDate;//주문한시간
    private String addr; //배송주소
    private Long customerId;
    private List<OrderDetailDTO> orderproductlist;



//    "orderproductlist":"{'productNo':'','orderPrice':'','count':''}"
}
