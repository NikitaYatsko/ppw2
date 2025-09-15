package com.example.ppw2.controller;

import com.example.ppw2.entity.User;
import com.example.ppw2.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final UserServiceImpl userService;

    @GetMapping
    @Operation(summary = "Приветствие", description = "Возвращает тестовую строку")
    public String hello() {
        return "aboba";
    }

    @GetMapping("/users")
    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен")
    })
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Получить пользователя по ID", description = "Возвращает пользователя по заданному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public User getUserById(
            @Parameter(description = "ID пользователя", example = "1")
            @PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users/create")
    @Operation(summary = "Создать нового пользователя", description = "Создает нового пользователя с указанными данными")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя")
    })
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Обновить пользователя", description = "Обновляет данные существующего пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Неверные данные пользователя")
    })
    public User updateUser(
            @Parameter(description = "ID пользователя для обновления", example = "1")
            @PathVariable Integer id,
            @Valid @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Удалить пользователя", description = "Удаляет пользователя по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public void deleteUser(
            @Parameter(description = "ID пользователя для удаления", example = "1")
            @PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
