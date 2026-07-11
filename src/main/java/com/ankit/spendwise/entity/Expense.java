package com.ankit.spendwise.entity;
import java.time.LocalDate;

public class Expense {

    private Long id;
    private Double amount;
    private String note;
    private LocalDate date;
    private String expenseCategory;

    public Expense() {

    }

    public Expense(Long id, Double amount, String note, LocalDate date, String expenseCategory) {
        this.id=id;
        this.amount=amount;
        this.note=note;
        this.date=date;
        this.expenseCategory=expenseCategory;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return expenseCategory;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public void setAmount(Double amount) {
        this.amount=amount;
    }

    public void setNote(String note) {
        this.note=note;
    }

    public void setDate(LocalDate date) {
        this.date=date;
    }

    public void setCategory(String category) {
        this.expenseCategory=category;
    }
}
