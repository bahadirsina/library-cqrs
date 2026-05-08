package com.turkcell.library_cqrs.application.features.student.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.student.mapper.StudentMapper;
import com.turkcell.library_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;


@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, CreatedCategoryResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;
    private final StudentMapper studentMapper;

    public CreateStudentCommandHandler(StudentRepository studentRepository, StudentBusinessRules studentBusinessRules, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
        this.studentMapper = studentMapper;
    }

    @Override
    public CreatedCategoryResponse handle(CreateStudentCommand command) {
        // Öğrenci oluşturma işlemi burada gerçekleştirilecek
        // Örneğin, veritabanına kaydedebilir ve oluşturulan öğrencinin ID'sini döndürebilirsiniz.
        // Örnek olarak rastgele bir UUID döndürülüyor

        studentBusinessRules.studentWithSameStudentNoMustNotExist(command.studentNo()); // rules

        Student student = studentMapper.studentFromCreateCommand(command); // mapper

        Student savedStudent = studentRepository.save(student); // repository

        CreatedCategoryResponse response = studentMapper.createdCategoryResponseFromStudent(savedStudent); // mapper
       
        return response;
    }

}
