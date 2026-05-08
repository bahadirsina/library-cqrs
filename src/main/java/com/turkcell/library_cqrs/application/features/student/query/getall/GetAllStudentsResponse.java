package com.turkcell.library_cqrs.application.features.student.query.getall;

import java.util.UUID;

public record GetAllStudentsResponse(UUID id, String name, String surname, String email, String phone, String studentNo, Integer age) {

}
