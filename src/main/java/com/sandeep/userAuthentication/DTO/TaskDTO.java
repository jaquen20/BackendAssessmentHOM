package com.sandeep.userAuthentication.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private boolean isCompleted;
    private LocalDateTime timestamp;
}
