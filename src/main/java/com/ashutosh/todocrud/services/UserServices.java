package com.ashutosh.todocrud.services;

import com.ashutosh.todocrud.entity.Users;
import com.ashutosh.todocrud.repository.UserRepository;
import com.ashutosh.todocrud.request.AddUserRequest;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServices {

    private UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    public void addUser(AddUserRequest userRequest){
        Users user = new Users();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        Users user = this.getUserById(userId);
        userRepository.delete(user);
    }

}
