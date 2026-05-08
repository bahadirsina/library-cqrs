package com.turkcell.library_cqrs.application.features.student.command.register;

import java.util.UUID;

public record RegisterResponse(UUID id,String name,String surname,String phone,String studentNo,Integer age,String email) {

}
