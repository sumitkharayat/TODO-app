package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.model.User;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserRepository userRepository;

    // GET /api/todos → get all todos for logged in user
    @GetMapping
    public List<Todo> getAllTodos(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todoService.getTodosByUser(user.getId());
    }

    // POST /api/todos → create new todo for logged in user
    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody Todo todo, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        return ResponseEntity.ok(todoService.createTodo(todo));
    }

    // PUT /api/todos/{id} → update todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,
                                           @RequestBody Todo todo,
                                           Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
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