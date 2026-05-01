package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepository userRepository;

    // Helper — get currently logged in user
    private User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // GET /api/todos → get only THIS user's todos
    @GetMapping
    public List<Todo> getAllTodos() {
        User user = getLoggedInUser();
        return todoService.getTodosByUser(user.getId());
    }

    // POST /api/todos → create todo for THIS user
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        User user = getLoggedInUser();
        todo.setUser(user);
        return todoService.createTodo(todo);
    }

    // PUT /api/todos/{id} → update todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @RequestBody Todo todo) {
        User user = getLoggedInUser();
        todo.setUser(user);
        return ResponseEntity.ok(todoService.updateTodo(id, todo));
    }

    // DELETE /api/todos/{id} → delete todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}