package org.example.model.DTOs.userDTO;

import lombok.Data;
import org.example.model.User;
import org.example.model.enumerations.Role;

@Data
public class UserDetailsDTO {
    private String username;
    private Role role;

    public static UserDetailsDTO of(User user){
        UserDetailsDTO details = new UserDetailsDTO();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
