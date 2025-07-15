package com.ecommerce.order.service;

import com.ecommerce.delivery.model.DeliveryBoy;
import com.ecommerce.delivery.repo.DeliveryBoyRepository;
import com.ecommerce.delivery.service.DeliveryBoyService;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryBoyService deliveryBoyService;

    public Order placeOrder(Order order) {
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());

        // Calculate total amount
        double total = order.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(total);

        // Set default order and payment status
        order.setOrderStatus("processing");
        order.setPaymentStatus("pending");

        // Assign available delivery boy
        DeliveryBoy deliveryBoy = deliveryBoyService.assignAvailableDeliveryBoy();
        deliveryBoy.setAvailable(false); // mark him unavailable
        deliveryBoy.setUpdatedAt(Instant.now());
        deliveryBoyService.updateDeliveryBoy(deliveryBoy); // update delivery boy record

        // Set delivery boy in order (this line is fixed)
        order.setDeliveryBoy(deliveryBoy);

        return orderRepository.save(order);
    }



    public List<Order> getOrdersByCustomer(UUID customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrderStatus(UUID orderId, String status) {
        Order order = getOrderById(orderId);

        // Validate status
        List<String> validStatuses = List.of("processing", "shipped", "delivered", "cancelled");
        if (!validStatuses.contains(status.toLowerCase())) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }

        order.setOrderStatus(status.toLowerCase());
        order.setUpdatedAt(Instant.now());

        // Optionally handle deliveryBoy availability if cancelled
        if ((status.equalsIgnoreCase("cancelled") || status.equalsIgnoreCase("delivered"))
                && order.getDeliveryBoy() != null) {
            deliveryBoyService.markAvailable(order.getDeliveryBoy().getId());
        }

        // Optionally set deliveredAt timestamp
        if ("delivered".equalsIgnoreCase(status)) {
            order.setDeliveredAt(Instant.now()); // Ensure you have a deliveredAt field in Order class
        }

        return orderRepository.save(order);
    }



    public Order updatePaymentStatus(UUID orderId, String status) {
        Order order = getOrderById(orderId);
        order.setPaymentStatus(status);
        order.setUpdatedAt(Instant.now());
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByDeliveryBoyId(UUID deliveryBoyId) {
        return orderRepository.findByDeliveryBoyId(deliveryBoyId);
    }


}
