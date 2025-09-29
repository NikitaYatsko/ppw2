package com.example.ppw2.service.Implementation;

import com.example.ppw2.entity.User;
import com.example.ppw2.entity.dto.UserResponseDTO;
import com.example.ppw2.exception.UserNotFoundException;
import com.example.ppw2.repository.UserRepository;
import com.example.ppw2.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getAge(),
                user.getEmail(),
                user.getOrders()
        );
    }

    private UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Integer id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setAge(user.getAge());
        userRepository.save(existingUser);
        return mapToDTO(existingUser);
    }


    @Override
    @Transactional
    public UserResponseDTO saveUser(User user) {
        userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);

    }
}
