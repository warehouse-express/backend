package com.inventory.controller.dto.response;

import com.inventory.model.entity.Order;
import com.inventory.model.entity.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO for returning order data via API responses
 */
@Data
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private BuyerSummaryDto buyer;
    private List<OrderItemResponseDto> items;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime placedAt;
    private LocalDateTime shippedAt;
    private LocalDateTime deliveredAt;
    private String shippingAddress;
    private String trackingNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * Convert an Order entity to an OrderResponseDto
     */
    public static OrderResponseDto fromEntity(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());

        //map buyer to summary DTO
        BuyerSummaryDto buyerDto = new BuyerSummaryDto();
        buyerDto.setId(order.getBuyer().getId());
        buyerDto.setFirstName(order.getBuyer().getFirstName());
        buyerDto.setLastName(order.getBuyer().getLastName());
        dto.setBuyer(buyerDto);

        //map order items
        dto.setItems(order.getItems().stream()
                .map(OrderItemResponseDto::fromEntity)
                .collect(Collectors.toList()));

        dto.setTotalAmount(order.getTotalAmount());
        dto.setStatus(order.getStatus().toString());
        dto.setPlacedAt(order.getPlacedAt());
        dto.setShippedAt(order.getShippedAt());
        dto.setDeliveredAt(order.getDeliveredAt());
        dto.setShippingAddress(order.getShippingAddress());
        dto.setTrackingNumber(order.getTrackingNumber());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setUpdatedAt(order.getUpdatedAt());

        return dto;
    }

    /**
     * Convert a list of Order entities to a list of OrderResponseDtos
     */
    public static List<OrderResponseDto> fromEntities(List<Order> orders) {
        return orders.stream()
                .map(OrderResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * DTO for summarizing buyer information within an order
     */
    @Data
    public static class BuyerSummaryDto {
        private Long id;
        private String firstName;
        private String lastName;
    }

    /**
     * DTO for representing order items within an order response
     */
    @Data
    public static class OrderItemResponseDto {
        private Long id;
        private ProductSummaryDto product;
        private Integer quantity;
        private BigDecimal price;
        private String productName;
        private String productDescription;

        /**
         * Convert an OrderItem entity to an OrderItemResponseDto
         */
        public static OrderItemResponseDto fromEntity(OrderItem item) {
            OrderItemResponseDto dto = new OrderItemResponseDto();
            dto.setId(item.getId());

            // Map product to summary DTO
            ProductSummaryDto productDto = new ProductSummaryDto();
            productDto.setId(item.getProduct().getId());
            productDto.setName(item.getProduct().getName());
            dto.setProduct(productDto);

            dto.setQuantity(item.getQuantity());
            dto.setPrice(item.getPrice());
            dto.setProductName(item.getProductName());
            dto.setProductDescription(item.getProductDescription());

            return dto;
        }
    }

    /**
     * DTO for summarizing product information within an order item
     */
    @Data
    public static class ProductSummaryDto {
        private Long id;
        private String name;
    }
}