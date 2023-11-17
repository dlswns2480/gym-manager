package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.dto.request.ApiCreateOrder;

import java.util.Collections;

public class OrderData {
    public static ApiCreateOrder getApiCreateOrder(){
        return new ApiCreateOrder(MemberData.getMember(), Collections.singletonList(ItemData.getItem()));
    }
    public static Order getOrder(){
        return Order.createOrder(getApiCreateOrder());
    }
}
