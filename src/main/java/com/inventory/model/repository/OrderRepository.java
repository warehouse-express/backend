package com.inventory.model.repository;

import com.inventory.model.entity.Buyer;
import com.inventory.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyer(Buyer buyer);
    Optional<Order> findByOrderNumber(String orderNumber);
}