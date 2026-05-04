package com.turkcell.library_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.core.mediator.Mediator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;


@RequestMapping("/api/students")
@RestController
public class StudentsController {
    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public UUID create(@RequestBody CreateStudentCommand command) {
        return mediator.send(command);
    }
    

}
