package com.devgym.gymmanager.order.application;

import com.devgym.gymmanager.member.application.MemberService;
import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.order.domain.Order;
import com.devgym.gymmanager.order.dto.mapper.OrderResponseMapper;
import com.devgym.gymmanager.order.dto.request.CreateOrderApiRequest;
import com.devgym.gymmanager.order.dto.request.CreateOrderRequest;
import com.devgym.gymmanager.order.dto.response.OrderResponse;
import com.devgym.gymmanager.order.repository.OrderRepository;
import com.devgym.gymmanager.orderitem.application.OrderItemService;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final OrderItemService orderItemService;

    @Transactional
    public OrderResponse createOrder(CreateOrderApiRequest request) {
        List<OrderItem> items = new ArrayList<>();
        for (Long id : request.itemIds()) {
            items.add(orderItemService.findByIdService(id));
        }
        Member member = memberService.findByIdService(request.memberId());
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(member, items);
        Order order = Order.createOrder(createOrderRequest); //생성 시 회원 set까지 한번에
        Order save = orderRepository.save(order);

        return OrderResponseMapper.toOrderResponse(save);
    }

    public List<OrderResponse> findAll() {
        List<Order> all = orderRepository.findAllWithMember();
        System.out.println(all.get(0).getFinalPrice() + ", " + all.get(0).getOrderItems());
        return all.stream()
            .map(OrderResponseMapper::toOrderResponse)
            .toList();
    }
}
