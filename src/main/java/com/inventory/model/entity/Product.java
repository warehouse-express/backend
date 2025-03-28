package com.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"seller.products", "orderItems"})
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    /*
    Cannot be null
    Must be zero or greater
    Uses BigDecimal for precise monetary calculations
    */
    @NotNull
    @Min(0)
    @Column(nullable = false)
    private BigDecimal price;

    @Min(0)
    @Column(nullable = false)
    private Integer quantity;

    @Column
    private String imageUrl;

    @Column
    private String category;

    /*
    Many products can belong to one seller
    Creates a "seller_id" foreign key column
    Makes the seller association required (not nullable)
    */
    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    /*
    One product can be in many order items
    The relationship is mapped by the "product" field in the OrderItem class
    Initializes the collection as an empty ArrayList
    Deleting a product deletes all OrderItems that include the product
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    /*
    Uses the Status enum defined in this class
    Stores the enum values as strings in the database
    Sets a default value of ACTIVE
    */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    //enum to define the statuses of the product
    public enum Status {
        ACTIVE, OUT_OF_STOCK, DISCONTINUED
    }
}