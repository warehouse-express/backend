package com.inventory.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    // Store product details at the time of order for historical records
    @Column(nullable = false)
    private String productName;

    @Column
    private String productDescription;

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice();
        this.productName = product.getName();
        this.productDescription = product.getDescription();
    }
}