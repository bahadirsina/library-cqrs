package com.turkcell.library_cqrs.application.features.student.command;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.security.jwt.JwtService;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;

@Component
public class LoginCommandHandler implements CommandHandler<LoginCommand, LoginResponse> {

    private final JwtService jwtService;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginCommandHandler(JwtService jwtService, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponse handle(LoginCommand command) {

        Student student = studentRepository.findByStudentNo(command.studentNo())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        if (!passwordEncoder.matches(command.password(), student.getPassword())) {
            throw new RuntimeException("Kullanıcı bulunamadı");
        }
        return new LoginResponse(jwtService.generate(student.getId(), student.getStudentNo()));
    }
}