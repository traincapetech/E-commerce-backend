package com.ecommerce.customer.dto;

import com.ecommerce.customer.model.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRegisterRequest {
    private String username;
    private String email;
    private String password;
    private String name;
    private String contactNumber;
    private List<Customer.Address> addresses;
}
