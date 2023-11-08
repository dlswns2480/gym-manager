package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.TestData.data.OrderData;
import com.devgym.gymmanager.dto.request.CreateOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {
    @Test
    @DisplayName("주문을 생성할 수 있다")
    void create() {
        CreateOrder createOrder = OrderData.getCreateOrder();
        Order order = Order.createOrder(createOrder);
        assertThat(order.getOrderItems().size()).isEqualTo(createOrder.orderItems().size());
    }
}