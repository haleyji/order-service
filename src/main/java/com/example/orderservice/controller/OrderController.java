package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.request.RequestOrder;
import com.example.orderservice.response.ResponseOrder;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order-service")
@RequiredArgsConstructor
public class OrderController {
    private final Environment env;
    private final OrderService ordereService;

    private final ModelMapper modelMapper;

    @GetMapping("/health_check")
    public String getStatus() {
        return String.format("It's working in Order Service in PORT %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId, @RequestBody RequestOrder requestOrder) {
        requestOrder.setUserId(userId);
        OrderDto orderDto = modelMapper.map(requestOrder, OrderDto.class);
        OrderDto orderedDto = ordereService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(orderDto, ResponseOrder.class));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable String userId) {
        log.info(">>>> user id is >>> {}", userId);
        Iterable<Order> orders = ordereService.getOrderByUserId(userId);
        List<ResponseOrder> responseOrders = new ArrayList<>();
        orders.forEach(o ->{
            responseOrders.add(modelMapper.map(o, ResponseOrder.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(responseOrders);
    }
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseOrder> getOrder(@PathVariable String orderId) {
        OrderDto orderDto = ordereService.getOrderByOrderId(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(orderDto, ResponseOrder.class));
    }


}
