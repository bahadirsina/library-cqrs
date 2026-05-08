package com.turkcell.library_cqrs.core.security.authorization;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(1) 
public class AuthorizationBehavior implements PipelineBehavior {

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("Authorization check performed.");
        return next.invoke();
    }

}
