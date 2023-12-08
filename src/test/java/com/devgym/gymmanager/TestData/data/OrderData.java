package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.order.dto.request.CreateOrderRequest;

import java.util.Collections;

public class OrderData {
    public static CreateOrderRequest getApiCreateOrder(){
        return new CreateOrderRequest(MemberData.getMember(), Collections.singletonList(ItemData.getItem()));
    }
    public static Order getOrder(){
        return Order.createOrder(getApiCreateOrder());
    }
}
