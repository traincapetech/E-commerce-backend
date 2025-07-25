package com.ecommerce.customer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // will be encoded

    private String name;

    private String contactNumber;

    @ElementCollection
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "customer_wishlist", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "product_id")
    private List<UUID> wishlist = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "customer_recently_viewed", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "product_id")
    private List<UUID> recentlyViewed = new ArrayList<>();

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @Embeddable
    @Getter
    @Setter
    public static class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
    }
}
