package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.dto.request.CreateOrder;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.dto.mapper.OrderResponseMapper;
import com.devgym.gymmanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    public OrderResponse createOrder(CreateOrder request) {
        Order order = Order.createOrder(request);
        Member member = memberService.findByIdService(request.memberId());
        Order save = orderRepository.save(order);
        save.setMember(member);
        return OrderResponseMapper.toOrderResponse(order);
    }

    public List<OrderResponse> findAll(){
        List<Order> all = orderRepository.findAll();
        return all.stream()
                .map(OrderResponseMapper::toOrderResponse)
                .toList();
    }
}
