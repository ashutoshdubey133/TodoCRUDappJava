package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Users;
import com.ashutosh.todocrud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    public Users addUser(Users user){
        return userRepository.save(user);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
