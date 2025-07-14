package com.ecommerce.delivery.service;

import com.ecommerce.delivery.model.DeliveryBoy;
import com.ecommerce.delivery.repo.DeliveryBoyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryBoyService {

    private final DeliveryBoyRepository deliveryBoyRepository;

    public DeliveryBoy addDeliveryBoy(DeliveryBoy deliveryBoy) {
        deliveryBoy.setAvailable(true);
        deliveryBoy.setCreatedAt(Instant.now());
        deliveryBoy.setUpdatedAt(Instant.now());
        return deliveryBoyRepository.save(deliveryBoy);
    }

    public DeliveryBoy assignAvailableDeliveryBoy() {
        return deliveryBoyRepository.findFirstByAvailableTrue()
                .orElseThrow(() -> new RuntimeException("No available delivery boys at the moment"));
    }

    public List<DeliveryBoy> getAllDeliveryBoys() {
        return deliveryBoyRepository.findAll();
    }

    public DeliveryBoy getById(UUID id) {
        return deliveryBoyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery boy not found"));
    }

    public void deleteById(UUID id) {
        deliveryBoyRepository.deleteById(id);
    }

    public DeliveryBoy getDeliveryBoyById(UUID id) {
        return deliveryBoyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery boy not found with ID: " + id));
    }


    public void markAvailable(UUID deliveryBoyId) {
        DeliveryBoy deliveryBoy = getDeliveryBoyById(deliveryBoyId);
        deliveryBoy.setAvailable(true);
        deliveryBoy.setUpdatedAt(Instant.now());
        deliveryBoyRepository.save(deliveryBoy);
    }

    public DeliveryBoy updateDeliveryBoy(DeliveryBoy deliveryBoy) {
        deliveryBoy.setUpdatedAt(Instant.now());
        return deliveryBoyRepository.save(deliveryBoy);
    }


}
