package com.example.ppw2.service;

import com.example.ppw2.entity.User;
import com.example.ppw2.entity.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Integer id);
    UserResponseDTO updateUser(Integer id, User user);
    UserResponseDTO saveUser(User user);
    void deleteUser(Integer id);
}
