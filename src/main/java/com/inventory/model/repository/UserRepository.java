package com.inventory.model.repository;

import com.inventory.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* 
This declares an interface named UserRepository that extends 
<Spring Data JPA's JpaRepository>. 
The generic parameters specify:
User: the entity type this repository will manage
Long: the type of the primary key of the User entity
*/ 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*  This declares a method that will find a User by their email address. 
    It returns an Optional<User> which might contain a User if found, 
    or be empty if no user with that email exists. Spring 
    Data JPA will automatically implement this method based on the method name. */
    Optional<User> findByEmail(String email);

    /*  
    This declares a method that checks if a User with the given 
    email exists in the database. It returns a boolean 
    (true if exists, false if not).Spring Data JPA automatically 
    implements this based on the method name.
    */
    boolean existsByEmail(String email);
}