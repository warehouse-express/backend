package com.inventory.controller;

import com.inventory.controller.dto.SellerDto;
import com.inventory.model.entity.Seller;
import com.inventory.service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {
    
    private final SellerService sellerService;
    
    @GetMapping
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.getSellerById(id);
        return ResponseEntity.ok(seller);
    }
    
    @PostMapping
    public ResponseEntity<Seller> createSeller(@Valid @RequestBody SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setFirstName(sellerDto.getFirstName());
        seller.setLastName(sellerDto.getLastName());
        seller.setEmail(sellerDto.getEmail());
        seller.setPassword(sellerDto.getPassword());
        seller.setCompanyName(sellerDto.getCompanyName());
        seller.setCompanyDescription(sellerDto.getCompanyDescription());
        seller.setContactPhone(sellerDto.getContactPhone());
        seller.setBusinessAddress(sellerDto.getBusinessAddress());
        seller.setTaxId(sellerDto.getTaxId());
        
        Seller createdSeller = sellerService.createSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeller);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @Valid @RequestBody SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setFirstName(sellerDto.getFirstName());
        seller.setLastName(sellerDto.getLastName());
        seller.setCompanyName(sellerDto.getCompanyName());
        seller.setCompanyDescription(sellerDto.getCompanyDescription());
        seller.setContactPhone(sellerDto.getContactPhone());
        seller.setBusinessAddress(sellerDto.getBusinessAddress());
        seller.setTaxId(sellerDto.getTaxId());
        
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        return ResponseEntity.ok(updatedSeller);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}