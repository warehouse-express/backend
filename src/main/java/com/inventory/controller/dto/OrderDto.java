package com.inventory.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long buyerId;
    
    @NotBlank
    private String shippingAddress;
    
    @NotEmpty
    private List<OrderItemDto> items;
}