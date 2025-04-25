package com.sandeep.userAuthentication.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class UsersTask {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean isCompleted=false;
    private LocalDateTime timestamp;
    @ManyToOne
    private UserEntity user;
}
