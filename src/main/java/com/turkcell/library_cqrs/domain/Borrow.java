package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @UuidGenerator()
    @Column(name = "id")
    private UUID id;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;

    @Column(name = "last_restitute_date")
    private LocalDateTime lastRestituteDate;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "borrow", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fine> fines = new ArrayList<>();

    @OneToOne(mappedBy = "borrow", cascade = CascadeType.ALL, orphanRemoval = true)
    private Restitute restitute;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getLastRestituteDate() {
        return lastRestituteDate;
    }

    public void setLastRestituteDate(LocalDateTime lastRestituteDate) {
        this.lastRestituteDate = lastRestituteDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void setFines(List<Fine> fines) {
        this.fines = fines;
    }

    public Restitute getRestitute() {
        return restitute;
    }

    public void setRestitute(Restitute restitute) {
        this.restitute = restitute;
    }
}
