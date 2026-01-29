package com.yunkhngn.backend.mapper;

import com.yunkhngn.backend.entity.Todo;
import com.yunkhngn.backend.dto.TodoResponse;

public class TodoMapper {

    public static TodoResponse toResponse(Todo todo) {
        TodoResponse res = new TodoResponse();
        res.setId(todo.getId());
        res.setTitle(todo.getTitle());
        res.setStatus(todo.getStatus());
        res.setCreatedAt(todo.getCreatedAt());
        res.setCompletedAt(todo.getCompletedAt());
        return res;
    }
}