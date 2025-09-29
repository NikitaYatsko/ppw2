package com.example.ppw2.controller;

import com.example.ppw2.entity.User;
import com.example.ppw2.entity.dto.UserResponseDTO;
import com.example.ppw2.service.Implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final UserServiceImpl userService;

    @GetMapping
    @Operation(summary = "Приветствие", description = "Возвращает тестовую строку")
    public String hello() {
        log.info("hello");
        return "aboba";
    }

    @GetMapping("/users")
    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен")
    })
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        log.info("getting users");
        var response = userService.getAllUsers();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по заданному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<UserResponseDTO> getUserById(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Integer id) {
        log.info("getting user by ID {}", id);
        var respone = userService.getUserById(id);
        return ResponseEntity.ok().body(respone);

    }

    @PostMapping("/users/create")
    @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя с указанными данными")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя")
    })
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody User user) {
        log.info("creating new user {}", user);
        var response = userService.saveUser(user);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Обновить пользователя", description = "Обновляет данные существующего пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя")
    })
    public ResponseEntity<UserResponseDTO> updateUser(
            @Parameter(description = "ID пользователя для обновления", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody User user) {
        log.info("updating user {}", user);
        var response = userService.updateUser(id, user);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID пользователя для удаления", example = "1")
            @PathVariable Integer id) {
        log.info("deleting user {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
