package com.inventory.service;

import com.inventory.exception.UserAlreadyExistsException;
import com.inventory.exception.UserNotFoundException;
import com.inventory.model.entity.Seller;
import com.inventory.model.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    
    private final SellerRepository sellerRepository;
    private final UserService userService;
    
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }
    
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Seller not found with id: " + id));
    }
    
    public Seller createSeller(Seller seller) {
        if (userService.existsByEmail(seller.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use: " + seller.getEmail());
        }
        
        // Encode the password
        seller.setPassword(userService.encodePassword(seller.getPassword()));
        
        // Set the role (should be already set in constructor, but ensure it's correct)
        seller.setRole(Seller.Role.SELLER);
        
        return sellerRepository.save(seller);
    }
    
    public Seller updateSeller(Long id, Seller sellerDetails) {
        Seller existingSeller = getSellerById(id);
        
        // Update fields
        existingSeller.setFirstName(sellerDetails.getFirstName());
        existingSeller.setLastName(sellerDetails.getLastName());
        existingSeller.setCompanyName(sellerDetails.getCompanyName());
        existingSeller.setCompanyDescription(sellerDetails.getCompanyDescription());
        existingSeller.setContactPhone(sellerDetails.getContactPhone());
        existingSeller.setBusinessAddress(sellerDetails.getBusinessAddress());
        existingSeller.setTaxId(sellerDetails.getTaxId());
        
        return sellerRepository.save(existingSeller);
    }
    
    public void deleteSeller(Long id) {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }
}