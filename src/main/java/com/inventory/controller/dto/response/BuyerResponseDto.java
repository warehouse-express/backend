package com.inventory.controller.dto.response;

import com.inventory.model.entity.Buyer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for returning buyer data via API responses
 */
@Data
public class BuyerResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String shippingAddress;
    private String billingAddress;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Convert a Buyer entity to a BuyerResponseDto
     */
    public static BuyerResponseDto fromEntity(Buyer buyer) {
        BuyerResponseDto dto = new BuyerResponseDto();
        dto.setId(buyer.getId());
        dto.setFirstName(buyer.getFirstName());
        dto.setLastName(buyer.getLastName());
        dto.setEmail(buyer.getEmail());
        dto.setShippingAddress(buyer.getShippingAddress());
        dto.setBillingAddress(buyer.getBillingAddress());
        dto.setPhoneNumber(buyer.getPhoneNumber());
        dto.setCreatedAt(buyer.getCreatedAt());
        dto.setUpdatedAt(buyer.getUpdatedAt());

        return dto;
    }

    /**
     * Convert a list of Buyer entities to a list of BuyerResponseDtos
     */
    public static List<BuyerResponseDto> fromEntities(List<Buyer> buyers) {
        return buyers.stream()
                .map(BuyerResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * DTO with buyer details and their order summary
     */
    @Data
    public static class BuyerWithOrdersDto extends BuyerResponseDto {
        private List<OrderSummaryDto> orders;

        /**
         * Convert a Buyer entity to a BuyerWithOrdersDto
         */
        public static BuyerWithOrdersDto fromEntityWithOrders(Buyer buyer) {
            BuyerWithOrdersDto dto = new BuyerWithOrdersDto();
            dto.setId(buyer.getId());
            dto.setFirstName(buyer.getFirstName());
            dto.setLastName(buyer.getLastName());
            dto.setEmail(buyer.getEmail());
            dto.setShippingAddress(buyer.getShippingAddress());
            dto.setBillingAddress(buyer.getBillingAddress());
            dto.setPhoneNumber(buyer.getPhoneNumber());
            dto.setCreatedAt(buyer.getCreatedAt());
            dto.setUpdatedAt(buyer.getUpdatedAt());

            // Map orders to summary DTOs
            dto.setOrders(buyer.getOrders().stream()
                    .map(order -> {
                        OrderSummaryDto orderDto = new OrderSummaryDto();
                        orderDto.setId(order.getId());
                        orderDto.setOrderNumber(order.getOrderNumber());
                        orderDto.setTotalAmount(order.getTotalAmount());
                        orderDto.setStatus(order.getStatus().toString());
                        orderDto.setPlacedAt(order.getPlacedAt());
                        return orderDto;
                    })
                    .collect(Collectors.toList()));

            return dto;
        }
    }

    /**
     * DTO for summarizing order information within a buyer
     */
    @Data
    public static class OrderSummaryDto {
        private Long id;
        private String orderNumber;
        private java.math.BigDecimal totalAmount;
        private String status;
        private LocalDateTime placedAt;
    }
}