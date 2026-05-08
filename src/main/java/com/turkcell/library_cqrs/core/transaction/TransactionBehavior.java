package com.turkcell.library_cqrs.core.transaction;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

/**
 * Transaction Behavior - Bütünlük Sağlama İçin
 *
 * Bu behavior, veritabanı işlemlerinin bütünlüğünü sağlar.
 * Eğer bir işlem sırasında hata olursa, tüm değişiklikleri geri alır (rollback).
 *
 * Ne için kullanılır:
 * - Birden fazla veritabanı işlemi içeren requestlerde
 * - Veri tutarlılığını korumak için
 * - Hata durumunda sistemin tutarlı durumda kalmasını sağlamak için
 *
 * Örnek: Öğrenci oluştururken hem öğrenci hem de ilişkili kayıtlar ekleniyorsa,
 * eğer birinde hata olursa hepsi geri alınır.
 */
@Component
@Order(4)
public class TransactionBehavior implements PipelineBehavior {

    private final PlatformTransactionManager transactionManager;

    public TransactionBehavior(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        // Transaction başlat
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            // İşlemi çalıştır
            R result = next.invoke();

            // Başarılı olursa commit et
            transactionManager.commit(status);

            System.out.println("TRANSACTION: Committed for " + request.getClass().getSimpleName());

            return result;

        } catch (Exception e) {
            // Hata olursa rollback yap
            transactionManager.rollback(status);

            System.out.println("TRANSACTION: Rolled back for " + request.getClass().getSimpleName() +
                " due to error: " + e.getMessage());

            throw e; // Hatayı yukarı fırlat
        }
    }
}