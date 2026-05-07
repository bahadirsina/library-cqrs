package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "fine")
public class Fine {

    @Id
    @UuidGenerator()
    @Column(name = "id")
    private UUID id;

    @Column(name = "paid_date")
    private LocalDateTime paidDate;

    @Column
    private Boolean paid;

    @Column(name = "date_fine_price")
    private Double finePrice;

    @ManyToOne
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDateTime paidDate) {
        this.paidDate = paidDate;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Double getFinePrice() {
        return finePrice;
    }

    public void setFinePrice(Double finePrice) {
        this.finePrice = finePrice;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
