package com.turkcell.library_cqrs.application.features.student.command.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

public record CreateStudentCommand(
    @NotBlank(message = "Öğrenci adı boş olamaz")
    @Size(max = 100, message = "Öğrenci adı maksimum 100 karakter olmalıdır")
    String name,
    
    @NotBlank(message = "Öğrenci soyadı boş olamaz")
    @Size(max = 100, message = "Öğrenci soyadı maksimum 100 karakter olmalıdır")
    String surname,
    
    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email adresi girin")
    @Size(max = 100, message = "Email maksimum 100 karakter olmalıdır")
    String email,
    
    @NotBlank(message = "Telefon numarası boş olamaz")
    @Size(max = 100, message = "Telefon numarası maksimum 100 karakter olmalıdır")
    String phone,
    
    @NotBlank(message = "Öğrenci numarası boş olamaz")
    @Size(max = 100, message = "Öğrenci numarası maksimum 100 karakter olmalıdır")
    String studentNo,

    @NotBlank(message = "Şifre boş olamaz")
    @Size(max = 100, message = "Şifre maksimum 100 karakter olmalıdır")
    String password,
    
    @NotNull(message = "Yaş boş olamaz")
    @Positive(message = "Yaş pozitif bir sayı olmalıdır")
    Integer age
) implements Command<CreatedStudentResponse> {

}
