package com.turkcell.library_cqrs.application.features.student.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.application.features.student.rule.StudentBusinessRules;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;

import java.util.UUID;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, CreatedCategoryResponse> {

    private final StudentRepository studentRepository;
    private final StudentBusinessRules studentBusinessRules;

    public CreateStudentCommandHandler(StudentRepository studentRepository, StudentBusinessRules studentBusinessRules) {
        this.studentRepository = studentRepository;
        this.studentBusinessRules = studentBusinessRules;
    }

    @Override
    public CreatedCategoryResponse handle(CreateStudentCommand command) {
        // Öğrenci oluşturma işlemi burada gerçekleştirilecek
        // Örneğin, veritabanına kaydedebilir ve oluşturulan öğrencinin ID'sini döndürebilirsiniz.
        // Örnek olarak rastgele bir UUID döndürülüyor

        studentBusinessRules.studentWithSameStudentNoMustNotExist(command.studentNo());

        Student student = new Student();
        student.setName(command.name());
        student.setSurname(command.surname());
        student.setEmail(command.email());
        student.setAge(command.age());
        student.setPhone(command.phone());
        student.setStudentNo(command.studentNo());

        Student savedStudent = studentRepository.save(student);

        CreatedCategoryResponse response = new CreatedCategoryResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getSurname(),
                savedStudent.getEmail(),
                savedStudent.getPhone(),
                savedStudent.getStudentNo(),
                savedStudent.getAge()
        );
        return response;
    }

}
