package com.ecommerce.vendor.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorRegisterRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String contactNumber;
}
