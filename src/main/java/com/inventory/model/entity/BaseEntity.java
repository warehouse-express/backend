package com.inventory.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
//registers auditingEntityListner to handle createdAt and updatedAt fields
@EntityListeners(AuditingEntityListener.class)
//abstract class to be extended by all entities
public abstract class BaseEntity {

    @CreatedDate
    //databse column name created_at, cannot be changed or null
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    //Spring Data notation that automatically updates the updatedAt field 
    //when the entity is updated
    @LastModifiedDate
    @Column(name = "updated_at")
    //declare field to store when an entity was last updated 
    private LocalDateTime updatedAt;
}