package com.inventory.model.repository;

import com.inventory.model.entity.Product;
import com.inventory.model.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(Seller seller);
    
    Page<Product> findBySeller(Seller seller, Pageable pageable);
    
    Page<Product> findByCategory(String category, Pageable pageable);
    
    /*
     * This custom query finds active products (with quantity > 0) 
     * whose names contain the search term, with case-insensitive matching 
     * and pagination.
     */
    @Query("SELECT p FROM Product p WHERE " +
           "p.status = 'ACTIVE' AND p.quantity > 0 AND " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Product> searchByName(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    /*
     * This expands the search to include both product name and description, 
     * still limited to active products with available quantity.
     */
    @Query("SELECT p FROM Product p WHERE " +
           "p.status = 'ACTIVE' AND p.quantity > 0 AND " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Page<Product> search(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    /*
     This counts the total number of products for a particular seller.
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.seller.id = :sellerId")
    Long countBySellerId(@Param("sellerId") Long sellerId);
    
    /*
     This calculates the total quantity of all products for a specific seller.
     */
    @Query("SELECT SUM(p.quantity) FROM Product p WHERE p.seller.id = :sellerId")
    Integer sumQuantityBySellerId(@Param("sellerId") Long sellerId);
    
    /*
    This finds products with quantity less than a specified threshold and with a 
    specific status (for inventory alerts).
     */
    List<Product> findByQuantityLessThanAndStatus(Integer quantity, Product.Status status);
    
    /*
    This finds products within a specific price range with pagination support.
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice, Pageable pageable);
}