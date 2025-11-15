package com.payment.service.controller;

import com.payment.service.dto.UserRequest;
import com.payment.service.dto.UserResponse;
import com.payment.service.model.User;
import com.payment.service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest request) {

        User created = userService.create(
                new User(null, request.getName(), request.getEmail())
        );

        UserResponse response = new UserResponse();
        response.setId(created.getId());
        response.setName(created.getName());
        response.setEmail(created.getEmail());

        return response;
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        User user = userService.findById(id);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll()
                .stream()
                .map(u -> {
                    UserResponse r = new UserResponse();
                    r.setId(u.getId());
                    r.setName(u.getName());
                    r.setEmail(u.getEmail());
                    return r;
                })
                .toList();
    }
}
