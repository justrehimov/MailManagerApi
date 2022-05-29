package com.mailmanager.controller;


import com.mailmanager.dto.request.UserRequest;
import com.mailmanager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRequest userRequest){
        userService.register(userRequest);
    }

    @GetMapping("/confirm/{token}")
    public void confirm(@PathVariable("token") String token){
        userService.confirmUser(token);
    }
}
