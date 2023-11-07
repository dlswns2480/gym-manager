package com.devgym.gymmanager.service;

import com.devgym.gymmanager.domain.entity.OrderItem;
import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;
    public Long createItem(CreateOrderItem orderItem){
        OrderItem item = OrderItem.createItem(orderItem);
        OrderItem save = itemRepository.save(item);
        return save.getId();
    }
}
