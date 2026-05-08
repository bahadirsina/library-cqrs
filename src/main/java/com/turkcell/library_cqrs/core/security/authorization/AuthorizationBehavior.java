package com.turkcell.library_cqrs.core.security.authorization;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(3) 
public class AuthorizationBehavior implements PipelineBehavior {

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("Authorization check performed.");
        return next.invoke();
    }

    // 2+ adet çalışan pipeline yazalım: 
    // 1. Performance Monitoring -> Belirli bir süreyi aşan requestleri uyarı olarak yakala. 3000ms 'i geçen requestler konsola kendi ismiyle birlikte bilgi düşsün. 
    // 2. Logging Behavior -> Tüm requestleri içindeki bilgi, dönen cevap nedir ayrı ayrı loglasın. (Konsol) 
    // 3. Transaction Behavior -> Araştıralım (Bütünlük sağlama için) ve uygulamaya çalışalım.

}
