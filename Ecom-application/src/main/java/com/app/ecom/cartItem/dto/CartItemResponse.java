package com.app.ecom.cartItem.dto;

import com.app.ecom.product.dto.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    String userId;
    List<ProductResponse> products;
    BigDecimal totalPrice;


}
