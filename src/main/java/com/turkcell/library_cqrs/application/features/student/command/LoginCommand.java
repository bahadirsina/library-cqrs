package com.turkcell.library_cqrs.application.features.student.command;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record LoginCommand(String studentNo, String password) implements Command<LoginResponse> {

}
