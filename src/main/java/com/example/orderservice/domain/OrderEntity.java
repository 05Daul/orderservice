package com.example.orderservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "myorder")
@Slf4j
//주문일반
public class OrderEntity {
    @Id
    @GeneratedValue
    private Long orderId;
    @CreationTimestamp
    private Date orderDate;//주문한시간
    private String addr; //배송주소
    //고객이 여러 건의 주문을 할 수 있으므로 - 다대일관계
//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private CustomerEntity  customer;
    private Long customerId;
    //주문일반1에 대해서 구매한 정보(orderDetail)
    @OneToMany(mappedBy =  "myorder",cascade = CascadeType.ALL)
     //@OneToMany(mappedBy = "myorder")
     private List<OrderProductEntity> orderproductlist = new ArrayList<>();

    public OrderEntity(String addr, Long customerId) {
        this.addr = addr;
        this.customerId = customerId;
    }
    //양방향매핑으로 양쪽 엔티티에서 각각에 대한 정보를 가지고 있어야 하므로 반영
    public void changeOrderDetailInfo(OrderProductEntity orderDetail){
        orderproductlist.add(orderDetail);
        orderDetail.setMyorder(this);
    }
    public static OrderEntity makeOrderEntity(String addr,Long customerId,
                                              List<OrderProductEntity> list){
        OrderEntity entity = new OrderEntity(addr,customerId);
        for(OrderProductEntity orderdetail:list){
            entity.changeOrderDetailInfo(orderdetail);
        }
        log.info("*****************************************") ;
        log.info("값:{}",entity.getOrderproductlist());
        return entity;//만들어진 OrderEntity를 리턴
    }
}











