package ua.opnu.restaurantmanagement1.dto;

import lombok.Data;
import ua.opnu.restaurantmanagement1.entity.Role;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
