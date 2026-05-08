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
        System.out.println("Loglama Çalışıyor.");
        return next.invoke();
    }

}
