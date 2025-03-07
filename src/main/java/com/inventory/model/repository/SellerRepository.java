package com.inventory.model.repository;

import com.inventory.model.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByEmail(String email);
    

    //Same as BuyerRepository query
    @Query("SELECT s FROM Seller s WHERE " +
           "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(s.companyName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Seller> search(@Param("searchTerm") String searchTerm);
}