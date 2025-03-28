package com.inventory.controller;

import com.inventory.controller.dto.ProductDto;
import com.inventory.controller.dto.response.ProductResponseDto;
import com.inventory.model.entity.Product;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //get endpoint to retrieve all products
    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        //calls service to get all products
        List<Product> products = productService.getAllProducts();
        //converts product entities to DTOs and returns them
        return ProductResponseDto.fromEntities(products);
    }

    //get endpoint to retrieve products by product ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        //calls service to get products by product ID
        Product product = productService.getProductById(id);
        //returns product DTO with 200 OK status
        return ResponseEntity.ok(ProductResponseDto.fromEntity(product));
    }

    //get enpoint to retrieve products by seller ID
    @GetMapping("/seller/{sellerId}")
    public List<ProductResponseDto> getProductsBySeller(@PathVariable Long sellerId) {
        //calls service to get products by seller ID
        List<Product> products = productService.getProductsBySeller(sellerId);
        //converts product entities to DTOs and returns
        return ProductResponseDto.fromEntities(products);
    }

    //get endpoint to retrieve product by category
    @GetMapping("/category/{category}")
    public List<ProductResponseDto> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ProductResponseDto.fromEntities(products);
    }

    //post endpoints to create a new product
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        //creates a product entity
        Product product = new Product();
        //sets properties from the dto
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());

        //calls service to create product with seller ID
        Product createdProduct = productService.createProduct(product, productDto.getSellerId());
        //returns created product with 201 CREATED status
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProductResponseDto.fromEntity(createdProduct));
    }

    //put endpoint to update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(productDto.getCategory());

        //calls service to update the product
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(ProductResponseDto.fromEntity(updatedProduct));
    }

    //delete endpoint to delete product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        //returns 204 NO CONTENT status
        return ResponseEntity.noContent().build();
    }
}