package com.turkcell.library_cqrs.core.mediator;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;


import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext context;
    
    public SpringMediator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

        return handler.handle(command);
    }

    @Override
    public <R> R send(Query<R> query) {
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return handler.handle(query);
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

}
