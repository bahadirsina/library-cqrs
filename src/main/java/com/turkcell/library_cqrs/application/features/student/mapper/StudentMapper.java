package com.turkcell.library_cqrs.application.features.student.mapper;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.student.command.create.CreateStudentCommand;
import com.turkcell.library_cqrs.application.features.student.command.create.CreatedStudentResponse;
import com.turkcell.library_cqrs.domain.Student;

@Component
public class StudentMapper {

    public Student studentFromCreateCommand(CreateStudentCommand command) {
        Student student = new Student();
        student.setName(command.name());
        student.setSurname(command.surname());
        student.setEmail(command.email());
        student.setPhone(command.phone());
        student.setPassword(command.password());
        student.setStudentNo(command.studentNo());
        student.setAge(command.age());
        return student;
    }

    public CreatedStudentResponse createdStudentResponseFromStudent(Student student) {
        return new CreatedStudentResponse(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPhone(),
                student.getStudentNo(),
                student.getAge()
        );
    }

}
