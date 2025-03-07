package com.inventory.controller;

import com.inventory.controller.dto.BuyerDto;
import com.inventory.model.entity.Buyer;
import com.inventory.service.BuyerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
@RequiredArgsConstructor
public class BuyerController {
    
    private final BuyerService buyerService;
    
    @GetMapping
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id);
        return ResponseEntity.ok(buyer);
    }
    
    @PostMapping
    public ResponseEntity<Buyer> createBuyer(@Valid @RequestBody BuyerDto buyerDto) {
        Buyer buyer = new Buyer();
        buyer.setFirstName(buyerDto.getFirstName());
        buyer.setLastName(buyerDto.getLastName());
        buyer.setEmail(buyerDto.getEmail());
        buyer.setPassword(buyerDto.getPassword());
        buyer.setShippingAddress(buyerDto.getShippingAddress());
        buyer.setBillingAddress(buyerDto.getBillingAddress());
        buyer.setPhoneNumber(buyerDto.getPhoneNumber());
        
        Buyer createdBuyer = buyerService.createBuyer(buyer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBuyer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Buyer> updateBuyer(@PathVariable Long id, @Valid @RequestBody BuyerDto buyerDto) {
        Buyer buyer = new Buyer();
        buyer.setFirstName(buyerDto.getFirstName());
        buyer.setLastName(buyerDto.getLastName());
        buyer.setShippingAddress(buyerDto.getShippingAddress());
        buyer.setBillingAddress(buyerDto.getBillingAddress());
        buyer.setPhoneNumber(buyerDto.getPhoneNumber());
        
        Buyer updatedBuyer = buyerService.updateBuyer(id, buyer);
        return ResponseEntity.ok(updatedBuyer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}