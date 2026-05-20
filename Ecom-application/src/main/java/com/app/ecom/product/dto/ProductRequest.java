package com.app.ecom.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;

    @JsonProperty("stock_quantity")
    private Integer stockQuantity;
    private String category;
    private String imageUrl;

}
