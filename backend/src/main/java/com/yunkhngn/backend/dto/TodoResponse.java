package com.yunkhngn.backend.dto;

import com.yunkhngn.backend.common.TodoStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class TodoResponse {
    private UUID id;
    private String title;
    private TodoStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}