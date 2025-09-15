package com.example.ppw2.service;

import com.example.ppw2.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User updateUser(Integer id, User user);
    User saveUser(User user);
    void deleteUser(Integer id);
}
