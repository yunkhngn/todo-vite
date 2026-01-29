package com.yunkhngn.backend.service;

import com.yunkhngn.backend.entity.Todo;
import com.yunkhngn.backend.entity.User;
import com.yunkhngn.backend.entity.TodoStatus;
import com.yunkhngn.backend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(User user, String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setStatus(TodoStatus.PENDING);
        todo.setUser(user);

        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByUser(User user) {
        return todoRepository.findByUserIdAndDeletedFalse(user.getId());
    }

    public Todo markDone(UUID todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setStatus(TodoStatus.DONE);
        todo.setCompletedAt(LocalDateTime.now());

        return todoRepository.save(todo);
    }

    public void deleteTodo(UUID todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setDeleted(true);
        todoRepository.save(todo);
    }
}