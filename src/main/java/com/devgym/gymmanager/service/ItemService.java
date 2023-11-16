package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.mapper.OrderItemMapper;
import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.dto.response.OrderItemResponse;
import com.devgym.gymmanager.exception.NotFoundInfoException;
import com.devgym.gymmanager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    @Transactional
    public Long createItem(CreateOrderItem orderItem){
        OrderItem item = OrderItem.createItem(orderItem);
        OrderItem save = itemRepository.save(item);
        return save.getId();
    }
    public List<OrderItemResponse> findAll(){
        List<OrderItem> all = itemRepository.findAll();
        return all.stream()
                .map(OrderItemMapper::toOrderItemResponse)
                .toList();
    }

    public OrderItem findByIdService(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(NotFoundInfoException::new);
    }
}
