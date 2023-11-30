package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.TestData.data.OrderData;
import com.devgym.gymmanager.order.dto.request.ApiCreateOrder;
import com.devgym.gymmanager.order.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    @DisplayName("주문을 생성할 수 있다")
    void create() {
        ApiCreateOrder createOrder = OrderData.getApiCreateOrder();
        Order order = Order.createOrder(createOrder);
        assertThat(order.getOrderItems().size()).isEqualTo(createOrder.orderItems().size());
    }
}