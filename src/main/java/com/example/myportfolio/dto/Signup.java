package com.example.myportfolio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Signup {
    private String name;
    private String username;
    private String email;
    private Long contact;
    private String address;
    private String role;
    private String password;
}
