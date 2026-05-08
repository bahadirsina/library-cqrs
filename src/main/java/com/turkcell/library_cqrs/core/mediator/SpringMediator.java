package com.turkcell.library_cqrs.core.mediator;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext context;
    private final List<PipelineBehavior> behaviors;
    
    public SpringMediator(ApplicationContext context, List<PipelineBehavior> behaviors) {
        this.context = context;
        this.behaviors = behaviors.stream().sorted(AnnotationAwareOrderComparator.INSTANCE).toList();
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

        return invokePipeline(command,() -> handler.handle(command));
    }

    @Override
    public <R> R send(Query<R> query) {
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return invokePipeline(query,() -> handler.handle(query));
    }

    public Object resolveHandler(Class<?> requestType, Class<?> handlerInterface) {
        Map<String, ?> candidates = context.getBeansOfType(handlerInterface);

        return candidates.values().stream()
                .filter(bean -> {
                    ResolvableType type = ResolvableType.forClass(bean.getClass()).as(handlerInterface);
                    Class<?> handledType = type.getGeneric(0).resolve();
                    return requestType.equals(handledType);
                })
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Handler Bulunumadı." + requestType.getSimpleName()));
    }

    private <R> R invokePipeline(Object request, RequestHandlerDelegate<R> handlerInvocation) {

        RequestHandlerDelegate<R> next = handlerInvocation;

        for (int i = behaviors.size() - 1; i >= 0; i--) {
            PipelineBehavior behavior = behaviors.get(i);
            if (!behavior.supports(request)) continue; 
                RequestHandlerDelegate<R> current = next;
                next = () -> behavior.handle(request, current);
            
        }
        return next.invoke();
    }

}
