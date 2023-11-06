package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.domain.type.Category;
import com.devgym.gymmanager.dto.CreateOrderItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private int price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;
    @Builder
    public OrderItem(CreateOrderItem orderItem){
        this.name = orderItem.name();
        this.category = orderItem.category();
        this.price = orderItem.price();
        this.quantity = orderItem.quantity();
    }
}
