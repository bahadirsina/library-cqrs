package com.turkcell.library_cqrs.application.features.student.command.register;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;

@Component
public class RegisterCommandHandler implements CommandHandler<RegisterCommand, RegisterResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;    
    private final PasswordEncoder passwordEncoder;

    public RegisterCommandHandler(StudentRepository studentRepository, StudentBusinessRules studentBusinessRules, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse handle(RegisterCommand command) {

        this.studentBusinessRules.studentWithSameStudentNoMustNotExist(command.studentNo()); // rules

        Student student = new Student();
        student.setName(command.name());
        student.setSurname(command.surname());
        student.setPhone(command.phone());
        student.setStudentNo(command.studentNo());
        student.setAge(command.age());
        student.setEmail(command.email());
        student.setPassword(passwordEncoder.encode(command.password()));
        // Kayıt işlemi burada gerçekleştirilecek
        // Örneğin, veritabanına kaydetme işlemi yapılabilir
        // Kayıt başarılı ise, yeni oluşturulan öğrencinin bilgilerini içeren bir RegisterResponse döndürülebilir

        // Bu örnekte, sadece basit bir response döndürüyoruz
        studentRepository.save(student); // repository

        return new RegisterResponse(
            java.util.UUID.randomUUID(), // id
            command.name(),
            command.surname(),
            command.phone(),
            command.studentNo(),
            command.age(),
            command.email()
        );
    }
    
}