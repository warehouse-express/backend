package com.inventory.controller;

import com.inventory.controller.dto.OrderDto;
import com.inventory.controller.dto.OrderItemDto;
import com.inventory.controller.dto.response.OrderResponseDto;
import com.inventory.model.entity.Order;
import com.inventory.model.entity.OrderItem;
import com.inventory.model.entity.Product;
import com.inventory.service.OrderService;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return OrderResponseDto.fromEntities(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(OrderResponseDto.fromEntity(order));
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<OrderResponseDto> getOrderByOrderNumber(@PathVariable String orderNumber) {
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        return ResponseEntity.ok(OrderResponseDto.fromEntity(order));
    }

    @GetMapping("/buyer/{buyerId}")
    public List<OrderResponseDto> getOrdersByBuyer(@PathVariable Long buyerId) {
        List<Order> orders = orderService.getOrdersByBuyer(buyerId);
        return OrderResponseDto.fromEntities(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        // Convert OrderItemDto to OrderItem
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto itemDto : orderDto.getItems()) {
            Product product = productService.getProductById(itemDto.getProductId());
            OrderItem orderItem = new OrderItem(product, itemDto.getQuantity());
            orderItems.add(orderItem);
        }

        Order createdOrder = orderService.createOrder(
                orderDto.getBuyerId(),
                orderItems,
                orderDto.getShippingAddress()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderResponseDto.fromEntity(createdOrder));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam Order.Status status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(OrderResponseDto.fromEntity(updatedOrder));
    }

    @PutMapping("/{id}/tracking")
    public ResponseEntity<OrderResponseDto> updateTrackingInfo(
            @PathVariable Long id,
            @RequestParam String trackingNumber) {
        Order updatedOrder = orderService.updateTrackingInfo(id, trackingNumber);
        return ResponseEntity.ok(OrderResponseDto.fromEntity(updatedOrder));
    }

    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}