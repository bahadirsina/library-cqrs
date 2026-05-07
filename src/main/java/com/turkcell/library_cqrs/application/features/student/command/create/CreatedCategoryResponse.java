package com.turkcell.library_cqrs.application.features.student.command.create;

import java.util.UUID;

public record CreatedCategoryResponse(
    UUID id,
    String name,
    String surname,
    String email,
    String phone,
    String studentNo,
    Integer age
) {

}
