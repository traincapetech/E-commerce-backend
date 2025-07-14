package com.ecommerce.delivery.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryBoy {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String name;

    private String phone;

    private boolean available = true;

    private Instant createdAt;

    private Instant updatedAt;
}
