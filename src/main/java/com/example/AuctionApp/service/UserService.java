package com.example.AuctionApp.service;

import com.example.AuctionApp.exception.UserNotFoundException;
import com.example.AuctionApp.model.User;
import com.example.AuctionApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User getLoggedInUser(Long id, String password){
        return userRepository.loginUser(id,password).orElseThrow(
                ()->new UserNotFoundException("User by id "+id+" was not found or password was incorrect"));
    }
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(()->new UserNotFoundException("User by id "+id+" was not found"));
    }

    public void deleteUserById(Long id){
        userRepository.deleteUserById(id);
    }
}
