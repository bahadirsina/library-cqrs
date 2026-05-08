package com.turkcell.library_cqrs.core.monitoring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(1)
public class PerformanceMonitoringBehavior implements PipelineBehavior {

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        long startTime = System.currentTimeMillis();

        try {
            return next.invoke();
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            if (duration > 3000) {
                System.out.println("PERFORMANCE WARNING: Request " + request.getClass().getSimpleName() +
                    " took " + duration + " ms (exceeded 3000 ms threshold)");
            }
        }
    }
}