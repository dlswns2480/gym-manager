package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.data.ItemData;
import com.devgym.gymmanager.TestData.data.MemberData;
import com.devgym.gymmanager.TestData.data.OrderData;
import com.devgym.gymmanager.member.application.MemberService;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.order.application.OrderService;
import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.orderitem.application.ItemService;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.order.dto.request.OrderApiRequest;
import com.devgym.gymmanager.order.dto.response.OrderResponse;
import com.devgym.gymmanager.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;
    @Mock
    MemberService memberService;
    @Mock
    ItemService itemService;
    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("주문을 생성할 수 있다")
    void create() {
        OrderItem item = ItemData.getItem();
        Member member = MemberData.getMember();
        Order order = OrderData.getOrder();
        ReflectionTestUtils.setField(item, "id", 1L);
        ReflectionTestUtils.setField(member, "id", 1L);
        order.setMember(member);

        when(itemService.findByIdService(any(Long.class))).thenReturn(item);
        when(memberService.findByIdService(any(Long.class))).thenReturn(member);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderResponse order1 = orderService.createOrder(new OrderApiRequest(member.getId(), Collections.singletonList(item.getId())));

        assertThat(order.getFinalPrice()).isEqualTo(order1.finalPrice());
    }

    @Test
    @DisplayName("주문을 조회할 수 있다")
    void findAll(){
        List<Order> all = new ArrayList<>();
        Order order = OrderData.getOrder();
        Member member = MemberData.getMember();
        order.setMember(member);
        all.add(order);

        when(orderRepository.findAllWithMember()).thenReturn(List.of(order));

        List<OrderResponse> result = orderService.findAll();

        assertAll(
                () -> assertThat(all.get(0).getMember().getName()).isEqualTo(result.get(0).memberName()),
                () -> assertThat(all.get(0).getOrderItems().size()).isEqualTo(result.get(0).items().size())
        );
    }
}