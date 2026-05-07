package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "restitute")
public class Restitute {

    @Id
    @UuidGenerator()
    @Column(name = "id")
    private UUID id;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "delay_day_count")
    private Integer delayDayCount;

    @OneToOne
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

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getDelayDayCount() {
        return delayDayCount;
    }

    public void setDelayDayCount(Integer delayDayCount) {
        this.delayDayCount = delayDayCount;
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
