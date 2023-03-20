package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.createdOrderId();

        Order order = Order.builder().orderDto(orderDetails).build();
        orderRepository.save(order);

        OrderDto returnDto = new ModelMapper().map(order, OrderDto.class);

        return returnDto;
    }


    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        OrderDto orderDto = new OrderDto();
        orderDto.createOrderDto(order);
        return orderDto;
    }

    @Override
    public Iterable<Order> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
