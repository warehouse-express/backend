package com.inventory.model.repository;

/* 
Imports:
Buyer entity from the application's entity package
Spring Data JPA's JpaRepository interface
Query annotation for custom JPQL queries
Param annotation for naming query parameters
Spring's Repository annotation
Java's List and Optional classes
*/ 

import com.inventory.model.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* 
This declares an interface named BuyerRepository that extends 
<Spring Data JPA's JpaRepository>. 
The generic parameters specify:
Buyer: the entity type this repository will manage
Long: the type of the primary key of the User entity
*/ 
@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

    Optional<Buyer> findByEmail(String email);
    
    /*
    This defines a custom JPQL query that searches for buyers whose 
    first name, last name, or email contains the search term. 
    The query:
    Uses LOWER() on both the database fields and search term to make the 
    search case-insensitive
    Uses CONCAT() with wildcard characters % to perform a contains search
    The @Param("searchTerm") annotation maps the method parameter to the 
    :searchTerm placeholder in the query
    Returns a list of all buyers that match any of these conditions
     */

    @Query("SELECT b FROM Buyer b WHERE " +
           "LOWER(b.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(b.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(b.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Buyer> search(@Param("searchTerm") String searchTerm);
}