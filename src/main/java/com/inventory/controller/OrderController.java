package com.inventory.controller;

import com.inventory.controller.dto.OrderDto;
import com.inventory.controller.dto.OrderItemDto;
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
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<Order> getOrderByOrderNumber(@PathVariable String orderNumber) {
        Order order = orderService.getOrderByOrderNumber(orderNumber);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/buyer/{buyerId}")
    public List<Order> getOrdersByBuyer(@PathVariable Long buyerId) {
        return orderService.getOrdersByBuyer(buyerId);
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDto orderDto) {
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
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam Order.Status status) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @PutMapping("/{id}/tracking")
    public ResponseEntity<Order> updateTrackingInfo(
            @PathVariable Long id,
            @RequestParam String trackingNumber) {
        Order updatedOrder = orderService.updateTrackingInfo(id, trackingNumber);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @DeleteMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }
}