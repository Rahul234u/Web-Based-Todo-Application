package com.myownimplementation.todoapp.controllers;

import com.myownimplementation.todoapp.NotBean.Todo;
import com.myownimplementation.todoapp.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
    private TodoRepository todoRepository;
    @Autowired
    public TodoControllerJpa(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todos")
    public String ListTodo(ModelMap model) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("todos", todoRepository.findByUsername(username));
        return "todos";
    }

    @GetMapping("/add-todo")
    public String showNewTodo(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "add-todo";
    }

    @PostMapping("/add-todo")
    public String addNewTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "add-todo";
        }
        String username = (String)model.get("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }

    @GetMapping("/delete-todo")
    public String removeTodo(@RequestParam int id){
        todoRepository.deleteById(id);
        return "redirect:todos";
    }

    @GetMapping("/update-todo")
    public String updateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "add-todo";
    }

    @PostMapping("/update-todo")
    public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "add-todo";
        }
        String username = (String)model.get("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }
}
