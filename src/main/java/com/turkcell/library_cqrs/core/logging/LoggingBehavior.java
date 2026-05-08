package com.turkcell.library_cqrs.core.logging;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(2)
public class LoggingBehavior implements PipelineBehavior {

    @Override
    public boolean supports(Object request) {
        return request instanceof LoggableRequest;
    }

     @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("LOGGING: Incoming request - " + request.getClass().getSimpleName() + ": " + request.toString());

        R response = next.invoke();

        System.out.println("LOGGING: Response for " + request.getClass().getSimpleName() + ": " + response.toString());

        return response;
    }

}
