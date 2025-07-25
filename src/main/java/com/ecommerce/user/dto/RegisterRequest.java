package com.ecommerce.user.dto;

import com.ecommerce.user.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
