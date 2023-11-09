package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.domain.type.OrderStatus;
import com.devgym.gymmanager.dto.request.CreateOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    private int finalPrice;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Builder(access = AccessLevel.PRIVATE)
    private Order(CreateOrder request) {
        for (OrderItem orderItem : request.orderItems()) {
            this.finalPrice += orderItem.getPrice() * orderItem.getQuantity();
            this.orderItems.add(orderItem);
            orderItem.setOrder(this);
            System.out.println(orderItem);
        }
        this.status = OrderStatus.ACCPETED;
    }

    public static Order createOrder(CreateOrder request) {
        if(request.orderItems().size() > 5){
            throw new IllegalStateException("한번에 5개까지만 주문 가능합니다");
        }
        List<OrderItem> lst = new ArrayList<>();
        return Order.builder().request(request).build();
    }
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

}
