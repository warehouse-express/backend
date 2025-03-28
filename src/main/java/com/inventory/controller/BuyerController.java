package com.inventory.controller;

import com.inventory.controller.dto.BuyerDto;
import com.inventory.controller.dto.response.BuyerResponseDto;
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
    public List<BuyerResponseDto> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAllBuyers();
        return BuyerResponseDto.fromEntities(buyers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuyerResponseDto> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id);
        return ResponseEntity.ok(BuyerResponseDto.fromEntity(buyer));
    }

    @GetMapping("/{id}/with-orders")
    public ResponseEntity<BuyerResponseDto.BuyerWithOrdersDto> getBuyerWithOrders(@PathVariable Long id) {
        Buyer buyer = buyerService.getBuyerById(id);
        return ResponseEntity.ok(BuyerResponseDto.BuyerWithOrdersDto.fromEntityWithOrders(buyer));
    }

    @PostMapping
    public ResponseEntity<BuyerResponseDto> createBuyer(@Valid @RequestBody BuyerDto buyerDto) {
        Buyer buyer = new Buyer();
        buyer.setFirstName(buyerDto.getFirstName());
        buyer.setLastName(buyerDto.getLastName());
        buyer.setEmail(buyerDto.getEmail());
        buyer.setPassword(buyerDto.getPassword());
        buyer.setShippingAddress(buyerDto.getShippingAddress());
        buyer.setBillingAddress(buyerDto.getBillingAddress());
        buyer.setPhoneNumber(buyerDto.getPhoneNumber());

        Buyer createdBuyer = buyerService.createBuyer(buyer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BuyerResponseDto.fromEntity(createdBuyer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuyerResponseDto> updateBuyer(@PathVariable Long id, @Valid @RequestBody BuyerDto buyerDto) {
        Buyer buyer = new Buyer();
        buyer.setFirstName(buyerDto.getFirstName());
        buyer.setLastName(buyerDto.getLastName());
        buyer.setShippingAddress(buyerDto.getShippingAddress());
        buyer.setBillingAddress(buyerDto.getBillingAddress());
        buyer.setPhoneNumber(buyerDto.getPhoneNumber());

        Buyer updatedBuyer = buyerService.updateBuyer(id, buyer);
        return ResponseEntity.ok(BuyerResponseDto.fromEntity(updatedBuyer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        buyerService.deleteBuyer(id);
        return ResponseEntity.noContent().build();
    }
}