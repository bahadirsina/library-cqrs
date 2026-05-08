package com.turkcell.library_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.student.command.LoginCommand;
import com.turkcell.library_cqrs.application.features.student.command.LoginResponse;
import com.turkcell.library_cqrs.application.features.student.command.register.RegisterCommand;
import com.turkcell.library_cqrs.application.features.student.command.register.RegisterResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final Mediator mediator;

    public AuthController(Mediator mediator) {
        this.mediator = mediator;
    }


    @PostMapping("register")
    public RegisterResponse postMethodName(@RequestBody @Valid RegisterCommand command) {
        
        return mediator.send(command);
    }

    @PostMapping("login")
    public LoginResponse postMethodName(@RequestBody @Valid LoginCommand command) {
        return mediator.send(command);
    }
    
    

}
