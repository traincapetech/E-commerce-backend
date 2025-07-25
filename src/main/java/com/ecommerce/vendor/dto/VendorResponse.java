package com.ecommerce.vendor.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorResponse {
    private UUID id;
    private String username;
    private String email;
    private String name;
    private String contactNumber;
}
