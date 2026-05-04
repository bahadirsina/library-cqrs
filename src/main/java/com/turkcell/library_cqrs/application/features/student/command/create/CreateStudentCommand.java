package com.turkcell.library_cqrs.application.features.student.command.create;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateStudentCommand(
    String name,
    String surname,
    String email,
    String phone,
    String studentNo,
    Integer age
) implements Command<UUID> {

}
