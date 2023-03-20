package com.example.orderservice.dto;

import com.example.orderservice.entity.Order;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
public class OrderDto {
    private String productId;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    public void createdOrderId(){
        this.orderId = UUID.randomUUID().toString();
    }


    public void createOrderDto(Order order) {
        this.productId = order.getProductId();
        this.quantity = order.getQuantity();
        this.unitPrice = order.getUnitPrice();
        this.totalPrice = order.getTotalPrice();
        this.orderId = order.getOrderId();
        this.userId = order.getUserId();
    }
}
