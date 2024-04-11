package com.bobocode.service;

import com.bobocode.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TodoList {
    HashMap<Long, TodoDto> todoMap = new HashMap<>();
    
    public void addNewTodo(TodoDto todo) {
        todoMap.put(todo.getId(), todo);
    }

    public void updateTodo(TodoDto todo) {
        TodoDto updateTodo = todoMap.getOrDefault(todo.getId(), null);
        if (updateTodo == null){
            return;
        }
        updateTodo.setText(todo.getText());
        updateTodo.setDone(todo.isDone());
    }

    public List<TodoDto> getAllTodos() {
        return todoMap.values().stream().toList();
    }
}
