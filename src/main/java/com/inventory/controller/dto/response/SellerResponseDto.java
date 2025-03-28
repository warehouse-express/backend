package com.inventory.controller.dto.response;

import com.inventory.model.entity.Seller;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for returning seller data via API responses
 */
@Data
public class SellerResponseDto  {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String companyDescription;
    private String contactPhone;
    private String businessAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Convert a Seller entity to a SellerResponseDto
     */
    public static SellerResponseDto fromEntity(Seller seller) {
        SellerResponseDto dto = new SellerResponseDto();
        dto.setId(seller.getId());
        dto.setFirstName(seller.getFirstName());
        dto.setLastName(seller.getLastName());
        dto.setEmail(seller.getEmail());
        dto.setCompanyName(seller.getCompanyName());
        dto.setCompanyDescription(seller.getCompanyDescription());
        dto.setContactPhone(seller.getContactPhone());
        dto.setBusinessAddress(seller.getBusinessAddress());
        dto.setCreatedAt(seller.getCreatedAt());
        dto.setUpdatedAt(seller.getUpdatedAt());

        return dto;
    }

    /**
     * Convert a list of Seller entities to a list of SellerResponseDtos
     */
    public static List<SellerResponseDto> fromEntities(List<Seller> sellers) {
        return sellers.stream()
                .map(SellerResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * DTO with seller details and their products
     */
    @Data
    public static class SellerWithProductsDto extends SellerResponseDto {
        private List<ProductSummaryDto> products;

        /**
         * Convert a Seller entity to a SellerWithProductsDto
         */
        public static SellerWithProductsDto fromEntityWithProducts(Seller seller) {
            SellerWithProductsDto dto = new SellerWithProductsDto();
            dto.setId(seller.getId());
            dto.setFirstName(seller.getFirstName());
            dto.setLastName(seller.getLastName());
            dto.setEmail(seller.getEmail());
            dto.setCompanyName(seller.getCompanyName());
            dto.setCompanyDescription(seller.getCompanyDescription());
            dto.setContactPhone(seller.getContactPhone());
            dto.setBusinessAddress(seller.getBusinessAddress());
            dto.setCreatedAt(seller.getCreatedAt());
            dto.setUpdatedAt(seller.getUpdatedAt());

            //map products to summary DTOs
            dto.setProducts(seller.getProducts().stream()
                    .map(product -> {
                        ProductSummaryDto productDto = new ProductSummaryDto();
                        productDto.setId(product.getId());
                        productDto.setName(product.getName());
                        productDto.setPrice(product.getPrice());
                        productDto.setCategory(product.getCategory());
                        productDto.setStatus(product.getStatus().toString());
                        return productDto;
                    })
                    .collect(Collectors.toList()));

            return dto;
        }
    }

    /**
     * DTO for summarizing product information within a seller
     */
    @Data
    public static class ProductSummaryDto {
        private Long id;
        private String name;
        private java.math.BigDecimal price;
        private String category;
        private String status;
    }
}