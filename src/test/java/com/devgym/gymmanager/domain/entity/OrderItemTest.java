package com.devgym.gymmanager.domain.entity;

import com.devgym.gymmanager.orderitem.domain.Category;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    @Test
    @DisplayName("주문 상품을 생성할 수 있다")
    void createItem() {
        CreateOrderItem itemA = new CreateOrderItem("itemA", Category.PROTEIN, 10000, 5);
        OrderItem item = OrderItem.createItem(itemA);
        assertAll(
                () -> assertThat(item.getName()).isEqualTo(itemA.name()),
                () -> assertThat(item.getPrice()).isEqualTo(itemA.price()),
                () -> assertThat(item.getQuantity()).isEqualTo(itemA.quantity()),
                () -> assertThat(item.getCategory()).isEqualTo(itemA.category())
        );
    }

    @Test
    @DisplayName("가격을 0원 이하로 설정할 경우 예외가 발생한다")
    void createItemPriceWithZero(){
        CreateOrderItem itemA = new CreateOrderItem("itemA", Category.PROTEIN, -1, 5);
        assertThrows(IllegalArgumentException.class, () -> OrderItem.createItem(itemA));
    }
}