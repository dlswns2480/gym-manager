package com.devgym.gymmanager.controller;

import com.devgym.gymmanager.dto.request.CreateOrder;
import com.devgym.gymmanager.dto.request.OrderApiRequest;
import com.devgym.gymmanager.dto.response.OrderResponse;
import com.devgym.gymmanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderApiRequest order){
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

}
