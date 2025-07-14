package com.ecommerce.order.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddress {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
