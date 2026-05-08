package com.turkcell.library_cqrs.application.features.student.command.register;

import org.hibernate.validator.constraints.Length;

import com.turkcell.library_cqrs.core.logging.LoggableRequest;
import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterCommand(

    @NotBlank String name,
    @NotBlank String surname,
    @NotBlank String phone,
    @NotBlank String studentNo,
    Integer age,
    @NotBlank @Email String email,
    @NotBlank @Length(min = 3) String password

) implements Command<RegisterResponse>, LoggableRequest {

}
