package com.inventory.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDto {
    @NotNull
    private Long productId;
    
    @NotNull
    @Min(1)
    private Integer quantity;
}

