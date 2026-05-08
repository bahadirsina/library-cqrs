package com.turkcell.library_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.student.command.register.RegisterCommand;
import com.turkcell.library_cqrs.application.features.student.command.register.RegisterResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

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
    public RegisterResponse postMethodName(@RequestBody RegisterCommand command) {
        
        return mediator.send(command);
    }
    

}
