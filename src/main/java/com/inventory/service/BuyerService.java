package com.inventory.service;

import com.inventory.exception.UserAlreadyExistsException;
import com.inventory.exception.UserNotFoundException;
import com.inventory.model.entity.Buyer;
import com.inventory.model.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerService {
    
    private final BuyerRepository buyerRepository;
    private final UserService userService;
    
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }
    
    public Buyer getBuyerById(Long id) {
        return buyerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Buyer not found with id: " + id));
    }
    
    public Buyer createBuyer(Buyer buyer) {
        if (userService.existsByEmail(buyer.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use: " + buyer.getEmail());
        }
        
        // Encode the password
        buyer.setPassword(userService.encodePassword(buyer.getPassword()));
        
        // Set the role (should be already set in constructor, but ensure it's correct)
        buyer.setRole(Buyer.Role.BUYER);
        
        return buyerRepository.save(buyer);
    }
    
    public Buyer updateBuyer(Long id, Buyer buyerDetails) {
        Buyer existingBuyer = getBuyerById(id);
        
        // Update fields
        existingBuyer.setFirstName(buyerDetails.getFirstName());
        existingBuyer.setLastName(buyerDetails.getLastName());
        existingBuyer.setShippingAddress(buyerDetails.getShippingAddress());
        existingBuyer.setBillingAddress(buyerDetails.getBillingAddress());
        existingBuyer.setPhoneNumber(buyerDetails.getPhoneNumber());
        
        return buyerRepository.save(existingBuyer);
    }
    
    public void deleteBuyer(Long id) {
        Buyer buyer = getBuyerById(id);
        buyerRepository.delete(buyer);
    }
}