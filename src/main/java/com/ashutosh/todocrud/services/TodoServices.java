package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.entity.Users;
import com.ashutosh.todocrud.repository.ToDoRepository;
import com.ashutosh.todocrud.request.AddTodoRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TodoServices {

    private final UserServices userServices;
    private final ToDoRepository toDoRepository;

    public TodoServices(UserServices userServices, ToDoRepository toDoRepository) {
        this.userServices = userServices;
        this.toDoRepository = toDoRepository;
    }

    public Todo getTodoById(Long todoId){
        return toDoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
    }

    public void addTodo(Long userId, AddTodoRequest todoRequest){
        Users user = userServices.getUserById(userId);
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        this.saveTodo(todo);
        userServices.saveUser(user);
    }

    public void saveTodo(Todo todo){
        toDoRepository.save(todo);
    }

    public void toggleTodoCompleted(Long todoId){
        Todo todo = this.getTodoById(todoId);
        todo.setCompleted(!todo.getCompleted());
        this.saveTodo(todo);
    }

    public void deleteTodo(Long userId,Long todoId){
        Users user = userServices.getUserById(userId);
        Todo todo = this.getTodoById(todoId);
        user.getTodoList().remove(todo);
        userServices.saveUser(user);
        toDoRepository.delete(todo);
    }


}
