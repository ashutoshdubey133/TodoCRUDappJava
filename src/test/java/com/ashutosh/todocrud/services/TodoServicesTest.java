package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Todo;
import com.ashutosh.todocrud.entity.Users;
import com.ashutosh.todocrud.repository.ToDoRepository;
import com.ashutosh.todocrud.repository.UserRepository;
import org.h2.engine.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServicesTest {

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserServices userServices;
    private TodoServices todoServices;

    private Todo todo;
    private Users user;

    @BeforeEach
    void setUp() {
        todo = new Todo();
        todo.setId(1L);
        todo.setContent("test Todo");
        user = new Users();
        user.setId(1L);
        user.setUsername("ashutosh");
        user.setPassword("password");
        this.todoServices = new TodoServices(userServices,toDoRepository,userRepository);
    }

    @Test
    void getTodoById() {
        when(toDoRepository.findById(1L)).thenReturn(Optional.ofNullable(todo));
        Assertions.assertEquals(todo,todoServices.getTodoById(1L));
    }

    @Test
    @Disabled
    void addTodo() {
        when(userServices.getUserById(1L)).thenReturn(user);
        when(toDoRepository.save(todo)).thenReturn(todo);
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    void toggleTodoCompleted() {
    }

    @Test
    void deleteTodo() {
    }
}