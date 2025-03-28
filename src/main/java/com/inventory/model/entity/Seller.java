package com.inventory.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"products"})
public class Seller extends User {

    @NotBlank
    @Column(nullable = false)
    private String companyName;

    @Column
    private String companyDescription;

    @Column
    private String contactPhone;

    @Column
    private String businessAddress;

    @Column
    private String taxId;

    /*
    One seller can have many products
    The relationship is mapped by the "seller" field in the Product class
    All operations cascade from Seller to its Product entities
    If a product is removed from the collection, it will be deleted from the database
    */
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Seller(String firstName, String lastName, String email, String password, String companyName) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setRole(Role.SELLER);
        this.companyName = companyName;
    }
}