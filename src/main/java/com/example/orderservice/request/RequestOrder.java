package com.example.orderservice.request;

import lombok.Data;

@Data
public class RequestOrder {
    private String productId;
    private String orderId;
    private String userId;
    private String productName;
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice;
}
