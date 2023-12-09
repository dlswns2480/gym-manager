package com.devgym.gymmanager.item.application;

import com.devgym.gymmanager.item.domain.Item;
import com.devgym.gymmanager.item.dto.mapper.ItemMapper;
import com.devgym.gymmanager.item.dto.request.ItemRequest;
import com.devgym.gymmanager.item.dto.response.ItemResponse;
import com.devgym.gymmanager.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.devgym.gymmanager.item.dto.mapper.ItemMapper.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemResponse createItem(ItemRequest request){
        Item item = new Item(request.category(), request.productName(), request.price(), request.stock());
        Item savedItem = itemRepository.save(item);
        return toItemOrderResponse(savedItem);
    }
    public List<ItemResponse> findAll(){
        List<Item> all = itemRepository.findAll();
        return all.stream()
                .map(ItemMapper::toItemOrderResponse)
                .toList();
    }
}
