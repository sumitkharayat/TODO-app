package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // Get todos by user
    public List<Todo> getTodosByUser(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    // Create a new todo
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    // Update existing todo
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo existing = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setPriority(updatedTodo.getPriority());
        existing.setStatus(updatedTodo.getStatus());
        existing.setDueDate(updatedTodo.getDueDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return todoRepository.save(existing);
    }

    // Delete a todo
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}