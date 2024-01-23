package com.devgym.gymmanager.order.dto.response;

import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;
import java.util.List;

public record OrderResponse(String memberName, int finalPrice, List<OrderItemResponse> items) {

}
