package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.domain.type.Category;
import com.devgym.gymmanager.dto.request.CreateOrderItem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;
    @Builder(access = AccessLevel.PRIVATE)
    private OrderItem(CreateOrderItem orderItem){
        this.name = orderItem.name();
        this.category = orderItem.category();
        this.price = orderItem.price();
        this.quantity = orderItem.quantity();
    }

    public static OrderItem createItem(CreateOrderItem request) {
        int price = request.price();
        if(price <= 0){
            throw new IllegalArgumentException("가격이 0원 이하일 수 없습니다");
        }
        return OrderItem.builder().orderItem(request).build();
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderItem orderItem = (OrderItem) object;
        return price == orderItem.price && quantity == orderItem.quantity && Objects.equals(id, orderItem.id) && Objects.equals(name, orderItem.name) && category == orderItem.category && Objects.equals(order, orderItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price, quantity, order);
    }
}
