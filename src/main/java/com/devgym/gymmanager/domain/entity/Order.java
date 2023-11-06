package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.domain.BaseEntity;
import com.devgym.gymmanager.domain.type.Category;
import com.devgym.gymmanager.domain.type.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderStatus status;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

}
