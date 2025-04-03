package com.inventory.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    @NotBlank
    private String name;
    
    private String description;
    
    @NotNull
    @Min(0)
    private BigDecimal price;
    
    @Min(0)
    private Integer quantity;
    
    private String category;
    
    private Long sellerId;
}