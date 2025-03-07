package com.inventory.service;

import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.entity.Product;
import com.inventory.model.entity.Seller;
import com.inventory.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final SellerService sellerService;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }
    
    public List<Product> getProductsBySeller(Long sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);
        return productRepository.findBySeller(seller);
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    
    @Transactional
    public Product createProduct(Product product, Long sellerId) {
        Seller seller = sellerService.getSellerById(sellerId);
        product.setSeller(seller);
        
        // Set default status if not provided
        if (product.getStatus() == null) {
            product.setStatus(Product.Status.ACTIVE);
        }
        
        // Update product status based on quantity
        if (product.getQuantity() <= 0) {
            product.setStatus(Product.Status.OUT_OF_STOCK);
        }
        
        return productRepository.save(product);
    }
    
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = getProductById(id);
        
        // Only update fields that are provided
        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        
        if (productDetails.getDescription() != null) {
            existingProduct.setDescription(productDetails.getDescription());
        }
        
        if (productDetails.getPrice() != null) {
            existingProduct.setPrice(productDetails.getPrice());
        }
        
        if (productDetails.getCategory() != null) {
            existingProduct.setCategory(productDetails.getCategory());
        }
        
        if (productDetails.getImageUrl() != null) {
            existingProduct.setImageUrl(productDetails.getImageUrl());
        }
        
        // Handle quantity separately to update status
        if (productDetails.getQuantity() != null) {
            existingProduct.setQuantity(productDetails.getQuantity());
            
            // Update status based on quantity
            if (existingProduct.getQuantity() <= 0) {
                existingProduct.setStatus(Product.Status.OUT_OF_STOCK);
            } else if (existingProduct.getStatus() == Product.Status.OUT_OF_STOCK) {
                existingProduct.setStatus(Product.Status.ACTIVE);
            }
        }
        
        // Allow explicit status changes (e.g., to DISCONTINUED)
        if (productDetails.getStatus() != null) {
            existingProduct.setStatus(productDetails.getStatus());
        }
        
        return productRepository.save(existingProduct);
    }
    
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
