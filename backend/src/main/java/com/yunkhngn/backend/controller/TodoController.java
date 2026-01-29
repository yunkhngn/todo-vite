package com.yunkhngn.backend.controller;

import com.yunkhngn.backend.dto.TodoRequest;
import com.yunkhngn.backend.dto.TodoResponse;
import com.yunkhngn.backend.entity.Todo;
import com.yunkhngn.backend.entity.User;
import com.yunkhngn.backend.mapper.TodoMapper;
import com.yunkhngn.backend.repository.UserRepository;
import com.yunkhngn.backend.service.TodoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;
    private final UserRepository userRepository;

    public TodoController(TodoService todoService, UserRepository userRepository) {
        this.todoService = todoService;
        this.userRepository = userRepository;
    }

    private User getUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public TodoResponse create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody TodoRequest req) {
        User user = getUser(userDetails);
        Todo todo = todoService.createTodo(user, req.getTitle());
        return TodoMapper.toResponse(todo);
    }

    @GetMapping
    public List<TodoResponse> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        User user = getUser(userDetails);
        return todoService.getTodosByUser(user)
                .stream()
                .map(TodoMapper::toResponse)
                .toList();
    }

    @PatchMapping("/{id}/done")
    public TodoResponse done(@PathVariable UUID id) {
        return TodoMapper.toResponse(todoService.markDone(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        todoService.deleteTodo(id);
    }
}