package com.ecommerce.delivery.repo;

import com.ecommerce.delivery.model.DeliveryBoy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryBoyRepository extends JpaRepository<DeliveryBoy, UUID> {
    Optional<DeliveryBoy> findFirstByAvailableTrue();
}
