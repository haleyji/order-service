package com.example.orderservice.entity;

import com.example.orderservice.dto.OrderDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order implements Serializable {
    //직렬화 하는 이유 데이터,,마샬링,,,언마샬링?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer unitPrice;
    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column(nullable = false, unique = true)
    private String orderId;


    @Builder
    public Order(OrderDto orderDto) {
        this.orderId = orderDto.getOrderId();
        this.userId = orderDto.getUserId();
        this.productId = orderDto.getProductId();
        this.quantity = orderDto.getQuantity();
        this.unitPrice = orderDto.getUnitPrice();
        this.totalPrice = orderDto.getUnitPrice() * orderDto.getQuantity();
    }
}
