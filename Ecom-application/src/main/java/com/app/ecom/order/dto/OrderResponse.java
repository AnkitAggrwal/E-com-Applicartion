package com.app.ecom.order.dto;

import com.app.ecom.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;
}
