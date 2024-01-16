package com.scorac.stockmanager.model;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String position;
    private String role;

//    public String getRole() {
//        return role != null ? role.name() : "ADMIN"; // Provide a default role (e.g., "user") if it's null
//    }
}

