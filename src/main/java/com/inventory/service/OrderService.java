package com.inventory.service;

import com.inventory.exception.InsufficientInventoryException;
import com.inventory.exception.OrderNotFoundException;
import com.inventory.model.entity.Buyer;
import com.inventory.model.entity.Order;
import com.inventory.model.entity.OrderItem;
import com.inventory.model.entity.Product;
import com.inventory.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final BuyerService buyerService;
    private final ProductService productService;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }
    
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with order number: " + orderNumber));
    }
    
    public List<Order> getOrdersByBuyer(Long buyerId) {
        Buyer buyer = buyerService.getBuyerById(buyerId);
        return orderRepository.findByBuyer(buyer);
    }
    
    @Transactional
    public Order createOrder(Long buyerId, List<OrderItem> items, String shippingAddress) {
        Buyer buyer = buyerService.getBuyerById(buyerId);
        
        Order order = new Order();
        order.setBuyer(buyer);
        order.setShippingAddress(shippingAddress);
        order.setStatus(Order.Status.PENDING);
        order.setPlacedAt(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.ZERO); // Set starting total to $0
        // Save the order first to get an ID
        Order savedOrder = orderRepository.save(order);
        
        // Process each item and add to order
        for (OrderItem item : items) {
            Product product = productService.getProductById(item.getProduct().getId());
            
            // Check if enough inventory
            if (product.getQuantity() < item.getQuantity()) {
                throw new InsufficientInventoryException(
                        "Not enough inventory for product: " + product.getName() + 
                        ". Available: " + product.getQuantity() + 
                        ", Requested: " + item.getQuantity());
            }
            
            // Create order item
            OrderItem orderItem = new OrderItem(product, item.getQuantity());
            savedOrder.addItem(orderItem);
            
            // Update product inventory
            product.setQuantity(product.getQuantity() - item.getQuantity());
            if (product.getQuantity() == 0) {
                product.setStatus(Product.Status.OUT_OF_STOCK);
            }
            productService.updateProduct(product.getId(), product);
        }
        
        // Save again with all items
        return orderRepository.save(savedOrder);
    }
    
    @Transactional
    public Order updateOrderStatus(Long orderId, Order.Status status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        
        // Update timestamps based on status
        switch (status) {
            case SHIPPED:
                order.setShippedAt(LocalDateTime.now());
                break;
            case DELIVERED:
                order.setDeliveredAt(LocalDateTime.now());
                break;
        }
        
        return orderRepository.save(order);
    }
    
    @Transactional
    public Order updateTrackingInfo(Long orderId, String trackingNumber) {
        Order order = getOrderById(orderId);
        order.setTrackingNumber(trackingNumber);
        return orderRepository.save(order);
    }
    
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = getOrderById(orderId);
        
        // Only pending orders can be cancelled
        if (order.getStatus() != Order.Status.PENDING && order.getStatus() != Order.Status.PROCESSING) {
            throw new IllegalStateException("Cannot cancel order that has been shipped or delivered");
        }
        
        // Return items to inventory
        for (OrderItem item : order.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            
            // Update product status if it was out of stock
            if (product.getStatus() == Product.Status.OUT_OF_STOCK) {
                product.setStatus(Product.Status.ACTIVE);
            }
            
            productService.updateProduct(product.getId(), product);
        }
        
        // Update order status
        order.setStatus(Order.Status.CANCELLED);
        orderRepository.save(order);
    }
}