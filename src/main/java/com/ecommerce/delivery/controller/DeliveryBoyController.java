package com.ecommerce.delivery.controller;

import com.ecommerce.delivery.model.DeliveryBoy;
import com.ecommerce.delivery.service.DeliveryBoyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery-boys")
@RequiredArgsConstructor
public class DeliveryBoyController {

    private final DeliveryBoyService deliveryBoyService;

    @Operation(summary = "Add a new delivery boy")
    @PostMapping
    public ResponseEntity<DeliveryBoy> addDeliveryBoy(@RequestBody DeliveryBoy deliveryBoy) {
        return ResponseEntity.ok(deliveryBoyService.addDeliveryBoy(deliveryBoy));
    }

    @Operation(summary = "Get all delivery boys")
    @GetMapping
    public ResponseEntity<List<DeliveryBoy>> getAllDeliveryBoys() {
        return ResponseEntity.ok(deliveryBoyService.getAllDeliveryBoys());
    }

    @Operation(summary = "Get delivery boy by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryBoy> getDeliveryBoy(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "UUID of the delivery boy",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        return ResponseEntity.ok(deliveryBoyService.getById(id));
    }

    @Operation(summary = "Delete delivery boy by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryBoy(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "UUID of the delivery boy to delete",
                    required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000",
                    schema = @Schema(type = "string", format = "uuid")
            )
            UUID id
    ) {
        deliveryBoyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
