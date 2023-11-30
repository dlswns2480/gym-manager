package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.order.dto.request.ApiCreateOrder;

import java.util.Collections;

public class OrderData {
    public static ApiCreateOrder getApiCreateOrder(){
        return new ApiCreateOrder(MemberData.getMember(), Collections.singletonList(ItemData.getItem()));
    }
    public static Order getOrder(){
        return Order.createOrder(getApiCreateOrder());
    }
}
