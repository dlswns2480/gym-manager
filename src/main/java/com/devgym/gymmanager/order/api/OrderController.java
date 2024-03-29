package com.devgym.gymmanager.order.api;

import com.devgym.gymmanager.order.application.OrderService;
import com.devgym.gymmanager.order.dto.request.CreateOrderApiRequest;
import com.devgym.gymmanager.order.dto.response.OrderResponse;
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

@Tag(name = "Order", description = "주문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성 요청", description = "주문 정보가 생성됩니다.", tags = {"OrderController"})
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderApiRequest order) {
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }

    @Operation(summary = "주문 조회 요청", description = "전체 주문 정보를 조회합니다.", tags = {"OrderController"})
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

}
