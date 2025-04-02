package com.inventory.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
//edit to push

@Entity
@Table(name = "buyers")
//primary key join column to link the buyer table with foreign key user table
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
@NoArgsConstructor
public class Buyer extends User {

    @Column
    private String shippingAddress;

    @Column
    private String billingAddress;

    @Column
    private String phoneNumber;

    /*
     A single buyer can have many orders
    The relationship is mapped by the "buyer" field in the Order class
    All operations (persist, remove, etc.) cascade from Buyer to its Order entities
    If an order is removed from the collection, it will be deleted from the database
     */
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    
    public Buyer(String firstName, String lastName, String email, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);
        setRole(Role.BUYER);
    }
}
