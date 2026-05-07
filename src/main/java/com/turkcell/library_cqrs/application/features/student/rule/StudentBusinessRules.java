package com.turkcell.library_cqrs.application.features.student.rule;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;

@Component
public class StudentBusinessRules {

    private final StudentRepository studentRepository;

    public StudentBusinessRules(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void studentWithSameStudentNoMustNotExist(String studentNo) {

        Student studentWithSameStudentNo = studentRepository.findByStudentNo(studentNo).orElse(null);
        if (studentWithSameStudentNo != null) {
            throw new RuntimeException("Aynı öğrenci numarasına sahip bir öğrenci zaten mevcut.");
        }
    }

}
