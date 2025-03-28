package com.inventory.controller.dto.response;

import com.inventory.model.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for returning product data via API responses
 */
@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private String status;
    private SellerSummaryDto seller;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Convert a Product entity to a ProductResponseDto
     */
    public static ProductResponseDto fromEntity(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategory(product.getCategory());
        dto.setStatus(product.getStatus().toString());

        //include only essential seller information
        SellerSummaryDto sellerDto = new SellerSummaryDto();
        sellerDto.setId(product.getSeller().getId());
        sellerDto.setCompanyName(product.getSeller().getCompanyName());
        dto.setSeller(sellerDto);

        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());

        return dto;
    }

    /**
     * Convert a list of Product entities to a list of ProductResponseDtos
     */
    public static List<ProductResponseDto> fromEntities(List<Product> products) {
        return products.stream()
                .map(ProductResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * DTO for summarizing seller information within a product
     */
    @Data
    public static class SellerSummaryDto {
        private Long id;
        private String companyName;
    }
}