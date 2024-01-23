package com.devgym.gymmanager.order.domain;

import com.devgym.gymmanager.common.BaseEntity;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.order.dto.request.CreateOrderRequest;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Order(CreateOrderRequest request) {
        for (OrderItem orderItem : request.orderItems()) {
            this.finalPrice += orderItem.getPrice() * orderItem.getQuantity();
            this.orderItems.add(orderItem);
            orderItem.setOrder(this);
            System.out.println(orderItem);
        }
        this.member = request.member();
        member.getOrders().add(this);
        this.status = OrderStatus.ACCPETED;
    }


    public static Order createOrder(CreateOrderRequest request) {
        if (request.orderItems().size() > 5) {
            throw new IllegalStateException("한번에 5개까지만 주문 가능합니다");
        }
        List<OrderItem> lst = new ArrayList<>();
        return Order.builder().request(request).build();
    }

    //테스트용 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

}
