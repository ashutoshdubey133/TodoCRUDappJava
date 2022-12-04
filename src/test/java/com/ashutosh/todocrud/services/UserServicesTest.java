package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Users;
import com.ashutosh.todocrud.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    private UserRepository userRepository;
    private UserServices userServices;

    private Users user;

    @BeforeEach
    void setUp() {
        user = new Users(1L,"ashutosh","password");
        this.userServices = new UserServices(userRepository);
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        Assertions.assertEquals(user,userServices.getUserById(1L));
    }

    @Test
    void addUser() {
        userServices.addUser(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    void deleteUser() {
        userServices.deleteUser(1L);
        verify(userRepository,times(1)).deleteById(1L);
    }
}