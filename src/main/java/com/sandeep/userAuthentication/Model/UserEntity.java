package com.sandeep.userAuthentication.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;


}
