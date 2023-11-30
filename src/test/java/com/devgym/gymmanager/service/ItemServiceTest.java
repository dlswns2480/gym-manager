package com.devgym.gymmanager.service;

import com.devgym.gymmanager.TestData.data.ItemData;
import com.devgym.gymmanager.orderitem.application.ItemService;
import com.devgym.gymmanager.orderitem.domain.OrderItem;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;
import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;
import com.devgym.gymmanager.orderitem.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    ItemRepository itemRepository;
    @InjectMocks
    ItemService itemService;

    @Test
    @DisplayName("아이템 생성에 성공해야한다")
    void createItem() {
        CreateOrderItem itemDto = ItemData.getItemDto();
        OrderItem item = ItemData.getItem();
        when(itemRepository.save(any(OrderItem.class))).thenReturn(item);

        Long itemId = itemService.createItem(itemDto);

        assertThat(item.getId()).isEqualTo(itemId);
    }

    @Test
    @DisplayName("가격이 0원 이하인 아이템을 생성 시 예외가 발생한다")
    void createWithWrongPrice() {
        CreateOrderItem request = ItemData.getItemDtoWithZeroPrice();

        assertThrows(IllegalArgumentException.class, () -> itemService.createItem(request));
    }

    @Test
    @DisplayName("모든 아이템을 조회할 수 있다")
    void findAll(){
        List<OrderItem> items = new ArrayList<>();
        items.add(ItemData.getItem());
        when(itemRepository.findAll()).thenReturn(items);

        List<OrderItemResponse> all = itemService.findAll();

        assertAll(
                () -> assertThat(items.get(0).getPrice()).isEqualTo(all.get(0).price()),
                () -> assertThat(items.get(0).getQuantity()).isEqualTo(all.get(0).quantity()),
                () -> assertThat(items.get(0).getName()).isEqualTo(all.get(0).name())
        );

    }

}