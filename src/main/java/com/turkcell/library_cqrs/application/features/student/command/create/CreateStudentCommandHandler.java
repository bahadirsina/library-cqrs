package com.turkcell.library_cqrs.application.features.student.command.create;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import java.util.UUID;

@Component
public class CreateStudentCommandHandler implements CommandHandler<CreateStudentCommand, UUID> {

    @Override
    public UUID handle(CreateStudentCommand command) {
        // Öğrenci oluşturma işlemi burada gerçekleştirilecek
        // Örneğin, veritabanına kaydedebilir ve oluşturulan öğrencinin ID'sini döndürebilirsiniz.
        System.out.println("Create Command Çalıştı "); // Örnek olarak rastgele bir UUID döndürülüyor
        return UUID.randomUUID();
    }

}
