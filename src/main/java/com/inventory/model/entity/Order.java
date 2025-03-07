package com.inventory.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    /*Many orders can belong to one buyer
    Creates a "buyer_id" foreign key column
    Makes the buyer association required (not nullable) 
    */
    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    /*
    One order can have many order items
    The relationship is mapped by the "order" field in the OrderItem class
    
    \/
    All operations cascade from Order to its OrderItem entities
    If an item is removed from the collection, it will be deleted from the database
    /\

    Initializes the collection as an empty ArrayList
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();


    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private LocalDateTime placedAt;

    @Column
    private LocalDateTime shippedAt;

    @Column
    private LocalDateTime deliveredAt;

    @Column
    private String shippingAddress;

    @Column
    private String trackingNumber;

    public enum Status {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    //Method that will be called before the entity is persisted(inserted) 
    //to the database
    @PrePersist
    public void prePersist() {
        if (orderNumber == null) {
            //Generates a randome 10 character uppercase order number if null
            orderNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
        }
        if (status == null) {
            //If status is null, set it to PENDING
            status = Status.PENDING;
        }
        if (placedAt == null) {
            //If placedAt is null, set it to the current date and time
            placedAt = LocalDateTime.now();
        }
    }//Close prePersist method

    //Method to calculate the total amount of the order
    /*
    Does this by:
    Streaming through all order items
    For each item, multiplying the price by the quantity
    Summing all the item totals
    Using functional programming with Java streams */
    public void calculateTotalAmount() {
        this.totalAmount = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    //Method to add item to order
    /* 
    Adds the item to the order's items collection
    Sets the order field of the item to this order
    Calculates the total amount of the order
    */
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        calculateTotalAmount();
    }

    //Method to remove item from order
    /*
    Removes the item from the order's items collection
    Sets the order field of the item to null
    Calculates the total amount of the order
    */
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
        calculateTotalAmount();
    }
}