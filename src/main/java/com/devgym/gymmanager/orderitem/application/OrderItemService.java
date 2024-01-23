package com.devgym.gymmanager.orderitem.application;

import static com.devgym.gymmanager.common.exception.ErrorCode.NOT_EXIST_ITEM;

import com.devgym.gymmanager.common.exception.CustomException;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.orderitem.dto.mapper.OrderItemMapper;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;
import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;
import com.devgym.gymmanager.orderitem.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long createOrderItem(CreateOrderItem orderItem) {
        OrderItem item = OrderItem.createItem(orderItem);
        OrderItem save = itemRepository.save(item);
        return save.getId();
    }

    public List<OrderItemResponse> findAll() {
        List<OrderItem> all = itemRepository.findAll();
        return all.stream()
            .map(OrderItemMapper::toOrderItemResponse)
            .toList();
    }

    public OrderItem findByIdService(Long itemId) {
        return itemRepository.findById(itemId)
            .orElseThrow(() -> new CustomException(NOT_EXIST_ITEM));
    }
}
