package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.OrderApiRequest;
import com.devgym.gymmanager.dto.response.ErrorResponse;
import com.devgym.gymmanager.dto.response.MemberResponse;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Order", description = "주문 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Operation(summary = "주문 생성 요청", description = "주문 정보가 생성됩니다.", tags = { "OrderController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 정보 생성 성공",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderApiRequest order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }
    @Operation(summary = "주문 조회 요청", description = "전체 주문 정보를 조회합니다.", tags = { "OrderController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

}
