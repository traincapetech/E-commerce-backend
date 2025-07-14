package com.ecommerce.order.controller;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Place a new order")
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.placeOrder(order));
    }

    @Operation(summary = "Get order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Order UUID",
                    required = true,
                    example = "3e274df3-bce2-4f37-9c69-0a4e0d9bb5fb",
                    schema = @Schema(type = "string", format = "uuid")
            ) UUID id
    ) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get all orders of a customer")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(
            @PathVariable("customerId")
            @Parameter(
                    name = "customerId",
                    description = "Customer UUID",
                    required = true,
                    example = "5f50c31d-2c14-4bd3-91bc-d064fd19f6ad",
                    schema = @Schema(type = "string", format = "uuid")
            ) UUID customerId
    ) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerId));
    }

    @Operation(summary = "Delete an order by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Order UUID",
                    required = true,
                    example = "3e274df3-bce2-4f37-9c69-0a4e0d9bb5fb",
                    schema = @Schema(type = "string", format = "uuid")
            ) UUID id
    ) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update order status")
    @PatchMapping("/{id}/order-status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Order UUID",
                    required = true,
                    example = "3e274df3-bce2-4f37-9c69-0a4e0d9bb5fb",
                    schema = @Schema(type = "string", format = "uuid")
            ) UUID id,
            @RequestParam("status") String status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    @Operation(summary = "Update payment status")
    @PatchMapping("/{id}/payment-status")
    public ResponseEntity<Order> updatePaymentStatus(
            @PathVariable("id")
            @Parameter(
                    name = "id",
                    description = "Order UUID",
                    required = true,
                    example = "3e274df3-bce2-4f37-9c69-0a4e0d9bb5fb",
                    schema = @Schema(type = "string", format = "uuid")
            ) UUID id,
            @RequestParam("status") String status
    ) {
        return ResponseEntity.ok(orderService.updatePaymentStatus(id, status));
    }

}
