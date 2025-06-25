package org.example.model.DTOs.userDTO;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String role;
}