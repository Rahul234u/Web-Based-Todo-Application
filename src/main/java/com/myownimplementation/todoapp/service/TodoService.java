package com.myownimplementation.todoapp.service;

import com.myownimplementation.todoapp.NotBean.Todo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int idCounter = 0;
    static{
        todos.add(new Todo(++idCounter, "Rahul", "Learning Spring Boot", LocalDate.now().plusYears(1),false));
        todos.add(new Todo(++idCounter, "Rahul", "Learning to Dance", LocalDate.now().plusMonths(3),false));
        todos.add(new Todo(++idCounter, "Rahul", "Learning to Cook", LocalDate.now().plusMonths(6),false));
    }
    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = t -> t.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, Boolean status){
        Todo todo = new Todo(++idCounter, username, description, targetDate, status);
        todos.add(todo);
    }

    public void removeTodoById(int id){
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todos.stream().filter(predicate).findFirst().get();
    }

    public void updateTodo(@Valid Todo todo) {
        removeTodoById(todo.getId());
        todos.add(todo);
    }
}
