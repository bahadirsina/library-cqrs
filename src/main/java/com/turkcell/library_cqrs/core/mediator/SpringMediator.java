package com.turkcell.library_cqrs.core.mediator;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;


import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

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
        ResolvableType handlerType = ResolvableType.forClassWithGenerics(handlerInterface, requestType);
        String[] beans = context.getBeanNamesForType(handlerType);
        if (beans.length == 0) {
            throw new IllegalStateException("Handler Bulunumadı." + requestType.getSimpleName());
        }
        return context.getBean(beans[0]);
    }

}
