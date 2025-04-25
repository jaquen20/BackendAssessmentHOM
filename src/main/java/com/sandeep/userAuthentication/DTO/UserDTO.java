package com.sandeep.userAuthentication.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
}
