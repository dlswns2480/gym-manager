package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.CreateOrderItem;
import com.devgym.gymmanager.dto.response.ErrorResponse;
import com.devgym.gymmanager.dto.response.OrderItemResponse;
import com.devgym.gymmanager.dto.response.TrainerResponse;
import com.devgym.gymmanager.service.ItemService;
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
@Tag(name = "OrderItem", description = "주문 상품 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    @Operation(summary = "주문 상품 생성 요청", description = "주문 상품 정보가 생성됩니다.", tags = { "ItemController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 상품 정보 생성 성공",
                    content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @PostMapping("/create")
    public ResponseEntity<Long> createItem(@RequestBody CreateOrderItem request){
        return new ResponseEntity<>(itemService.createItem(request), HttpStatus.OK);
    }
    @Operation(summary = "주문 상품 조회 요청", description = "주문 상품 정보를 전체 조회합니다.", tags = { "ItemController" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 상품 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),

    })
    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }
}
