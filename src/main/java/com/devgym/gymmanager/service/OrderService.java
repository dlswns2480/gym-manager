package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.entity.Order;
import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.mapper.OrderResponseMapper;
import com.devgym.gymmanager.dto.request.ApiCreateOrder;
import com.devgym.gymmanager.dto.request.CreateOrder;
import com.devgym.gymmanager.dto.request.OrderApiRequest;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final ItemService itemService;
    @Transactional
    public OrderResponse createOrder(OrderApiRequest request) {
        List<OrderItem> items = new ArrayList<>();
        for(Long id : request.itemIds()){
            items.add(itemService.findByIdService(id));
        }
        CreateOrder createOrderDto = new CreateOrder(request.memberId(), items);
        Member member = memberService.findByIdService(request.memberId());
        ApiCreateOrder apiCreateOrder = new ApiCreateOrder(member, items);
        Order order = Order.createOrder(apiCreateOrder); //생성 시 회원 set까지 한번에
        Order save = orderRepository.save(order);

        return OrderResponseMapper.toOrderResponse(save);
    }

    public List<OrderResponse> findAll(){
        List<Order> all = orderRepository.findAllWithMember();
        System.out.println(all.get(0).getFinalPrice() + ", " + all.get(0).getOrderItems());
        return all.stream()
                .map(OrderResponseMapper::toOrderResponse)
                .toList();
    }
}
