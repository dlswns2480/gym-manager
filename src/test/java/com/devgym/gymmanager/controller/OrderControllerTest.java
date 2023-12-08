package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.jwt.JwtUtil;
import com.devgym.gymmanager.orderitem.domain.Category;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;
import com.devgym.gymmanager.order.dto.request.CreateOrderApiRequest;
import com.devgym.gymmanager.order.dto.response.OrderResponse;
import com.devgym.gymmanager.orderitem.application.OrderItemService;
import com.devgym.gymmanager.order.application.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@DisplayName("[OrderController Test]")
class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Value("${jwt.access-secret}")
    private String accessSecret;
    private final HttpHeaders httpHeaders = new HttpHeaders();
    @BeforeEach
    void setUp(){
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + JwtUtil.createAccessToken("injun@naver.com", accessSecret, 1000 * 60L));
        httpHeaders.add("Token-Type", "access");
    }
    @AfterEach
    void tearDown(){

    }
    @Test
    @DisplayName("주문을 생성할 수 있다")
    void createOrder() throws Exception{
        Member member = MemberData.getMember();
        Member save = memberRepository.save(member);
        CreateOrderItem itemA = new CreateOrderItem("itemA", Category.PROTEIN, 4000, 3);
        Long itemId = orderItemService.createOrderItem(itemA);
        CreateOrderApiRequest createOrderApiRequest = new CreateOrderApiRequest(member.getId(), Collections.singletonList(itemId));

        mvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders)
                .content(asJsonString(createOrderApiRequest)))
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
        Long itemId = orderItemService.createOrderItem(itemA);
        CreateOrderApiRequest createOrderApiRequest = new CreateOrderApiRequest(member.getId(), Collections.singletonList(itemId));
        OrderResponse order = orderService.createOrder(createOrderApiRequest);

        mvc.perform(get("/order")
                        .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberName").value(order.memberName()))
                .andExpect(jsonPath("$[0].items[0].name").value(itemA.name()))
                .andExpect(jsonPath("$[0].items[0].category").value(itemA.category().toString()));
    }
}