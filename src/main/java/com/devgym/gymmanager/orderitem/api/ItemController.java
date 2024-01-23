package com.devgym.gymmanager.orderitem.api;

import com.devgym.gymmanager.orderitem.application.OrderItemService;
import com.devgym.gymmanager.orderitem.dto.request.CreateOrderItem;
import com.devgym.gymmanager.orderitem.dto.response.OrderItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OrderItem", description = "주문 상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final OrderItemService orderItemService;

    @Operation(summary = "주문 상품 생성 요청", description = "주문 상품 정보가 생성됩니다.", tags = {"ItemController"})
    @PostMapping("/create")
    public ResponseEntity<Long> createItem(@RequestBody CreateOrderItem request) {
        return new ResponseEntity<>(orderItemService.createOrderItem(request), HttpStatus.OK);
    }

    @Operation(summary = "주문 상품 조회 요청", description = "주문 상품 정보를 전체 조회합니다.", tags = {
        "ItemController"})
    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAll() {
        return new ResponseEntity<>(orderItemService.findAll(), HttpStatus.OK);
    }
}
