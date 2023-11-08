package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.OrderData;
import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Category;
import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.dto.request.OrderApiRequest;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.service.ItemService;
import com.devgym.gymmanager.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@DisplayName("[OrderController Test]")
class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    OrderService orderService;
    @Autowired
    ItemService itemService;
    @Test
    @DisplayName("주문을 생성할 수 있다")
    void createOrder() throws Exception{
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        CreateOrderItem itemA = new CreateOrderItem("itemA", Category.PROTEIN, 4000, 3);
        Long itemId = itemService.createItem(itemA);
        OrderApiRequest orderApiRequest = new OrderApiRequest(member.getId(), Collections.singletonList(itemId));

        mvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(orderApiRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberName").value(save.getName()))
                .andExpect(jsonPath("$.finalPrice").value(itemA.price() * itemA.quantity()));
    }
    @Test
    @DisplayName("주문을 조회할 수 있다")
    void findAll() throws Exception{
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        CreateOrderItem itemA = new CreateOrderItem("itemA", Category.PROTEIN, 4000, 3);
        Long itemId = itemService.createItem(itemA);
        OrderApiRequest orderApiRequest = new OrderApiRequest(member.getId(), Collections.singletonList(itemId));
        OrderResponse order = orderService.createOrder(orderApiRequest);

        mvc.perform(get("/order")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value(order.memberName()))
                .andExpect(jsonPath("$[0].items[0].name").value(itemA.name()))
                .andExpect(jsonPath("$[0].items[0].category").value(itemA.category().toString()));
    }
}