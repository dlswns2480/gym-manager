package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.dto.response.OrderItemResponse;
import com.devgym.gymmanager.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    @ApiOperation(value = "상품 생성", notes = "주문을 위한 상품을 생성한다")
    @PostMapping("/create")
    public ResponseEntity<Long> createItem(@RequestBody CreateOrderItem request){
        return new ResponseEntity<>(itemService.createItem(request), HttpStatus.OK);
    }

    @ApiOperation(value = "상품 조회", notes = "등록된 상품을 전체 조회한다")
    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }
}
