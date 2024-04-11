package com.bobocode.controller;

import com.bobocode.dto.TodoDto;
import com.bobocode.service.TodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@RequiredArgsConstructor
public class TodoListController {

    private final TodoList todoList;

    @GetMapping("/todos")
    public String index(Model model) {
        model.addAttribute("todo", new TodoDto(ThreadLocalRandom.current().nextLong(), "", false));
        model.addAttribute("todos", todoList.getAllTodos());
        return "todo-list";
    }

    @PostMapping("/todos")
    public String handleFormSubmit(@ModelAttribute TodoDto todoDto, Model model) {
        if (todoDto.getId() == null) {
            todoList.addNewTodo(todoDto);
        } else {
            todoList.updateTodo(todoDto);
        }
        return index(model);
    }
}
