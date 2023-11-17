package com.devgym.gymmanager.TestData.data;

import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.dto.request.ApiCreateOrder;
import com.devgym.gymmanager.dto.request.CreateOrder;

import java.util.Collections;

public class OrderData {
    public static CreateOrder getCreateOrder(){
        return new CreateOrder(1L, Collections.singletonList(ItemData.getItem()));
    }
    public static ApiCreateOrder getApiCreateOrder(){
        return new ApiCreateOrder(MemberData.getMember(), Collections.singletonList(ItemData.getItem()));
    }
    public static Order getOrder(){
        return Order.createOrder(getApiCreateOrder());
    }
}
