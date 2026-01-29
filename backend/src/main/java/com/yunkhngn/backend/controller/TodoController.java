package com.yunkhngn.backend.controller;

import com.yunkhngn.backend.dto.TodoRequest;
import com.yunkhngn.backend.dto.TodoResponse;
import com.yunkhngn.backend.entity.Todo;
import com.yunkhngn.backend.entity.User;
import com.yunkhngn.backend.mapper.TodoMapper;
import com.yunkhngn.backend.repository.UserRepository;
import com.yunkhngn.backend.service.TodoService;
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

    // TẠM: lấy user đầu tiên trong DB
    private User getMockUser() {
        return userRepository.findAll().get(0);
    }

    @PostMapping
    public TodoResponse create(@RequestBody TodoRequest req) {
        Todo todo = todoService.createTodo(getMockUser(), req.getTitle());
        return TodoMapper.toResponse(todo);
    }

    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.getTodosByUser(getMockUser())
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