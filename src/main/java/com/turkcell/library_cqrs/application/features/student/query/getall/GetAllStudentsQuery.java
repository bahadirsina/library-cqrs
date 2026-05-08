package com.turkcell.library_cqrs.application.features.student.query.getall;


import org.springframework.data.domain.Page;

import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

public record GetAllStudentsQuery(int pageNumber, int pageSize) implements Query<Page<GetAllStudentsResponse>> {




}
