package com.turkcell.library_cqrs.application.features.student.query.getall;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.domain.Student;
import com.turkcell.library_cqrs.persistence.repository.StudentRepository;

@Component
public class GetAllStudentsQueryHandler implements QueryHandler<GetAllStudentsQuery, Page<GetAllStudentsResponse>> {

    private final StudentRepository studentRepository;

    public GetAllStudentsQueryHandler(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<GetAllStudentsResponse> handle(GetAllStudentsQuery query) {
        Pageable pageable = PageRequest.of(query.pageNumber(), query.pageSize());
        
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(student -> new GetAllStudentsResponse(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPhone(),
                student.getStudentNo(),
                student.getAge()
        ));
    }

}