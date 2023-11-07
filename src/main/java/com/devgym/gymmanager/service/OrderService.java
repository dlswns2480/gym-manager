package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.request.CreateOrder;
import com.devgym.gymmanager.dto.response.OrderItemMapper;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.dto.response.OrderResponseMapper;
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

    public Long createOrder(CreateOrder request) {
        Order order = Order.createOrder(request);
        Order save = orderRepository.save(order);
        return save.getId();
    }

    public List<OrderResponse> findAll(){
        List<Order> all = orderRepository.findAll();
        return all.stream()
                .map(OrderResponseMapper::toOrderResponse)
                .toList();
    }
}
