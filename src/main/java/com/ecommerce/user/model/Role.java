package com.ecommerce.user.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum Role {
    SUPER_ADMIN, ADMIN, VENDOR, CUSTOMER, DELIVERY_BOY;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}
