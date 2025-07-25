package com.ecommerce.order.model;

import com.ecommerce.delivery.model.DeliveryBoy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders") // avoid clash with SQL reserved keyword "order"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private UUID customerId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private double totalAmount;

    @Embedded
    private ShippingAddress shippingAddress;

    private String orderStatus;   // e.g., processing, shipped, delivered, cancelled
    private String paymentStatus; // e.g., pending, completed, failed

    private Instant createdAt;
    private Instant updatedAt;

    private Instant deliveredAt;

    @ManyToOne
    @JoinColumn(name = "delivery_boy_id")
    private DeliveryBoy deliveryBoy;
}
