package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.dto.response.OrderItemResponse;
import com.devgym.gymmanager.service.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "OrderItem", description = "주문 상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    @PostMapping("/create")
    public ResponseEntity<Long> createItem(@RequestBody CreateOrderItem request){
        return new ResponseEntity<>(itemService.createItem(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }
}
