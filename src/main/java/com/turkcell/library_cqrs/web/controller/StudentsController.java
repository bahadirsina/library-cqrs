package com.turkcell.library_cqrs.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.library_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.application.features.student.command.create.CreatedStudentResponse;
import com.turkcell.library_cqrs.application.features.student.query.getall.GetAllStudentsQuery;
import com.turkcell.library_cqrs.application.features.student.query.getall.GetAllStudentsResponse;
import com.turkcell.library_cqrs.core.mediator.Mediator;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/api/students")
@RestController
public class StudentsController {
    private final Mediator mediator;

    public StudentsController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreatedStudentResponse create(@Valid @RequestBody CreateStudentCommand command) {
        return mediator.send(command);
    }
    @GetMapping
    public Page<GetAllStudentsResponse> getAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        var query = new GetAllStudentsQuery(pageNumber, pageSize);
        return mediator.send(query);
    }
    
    

}
