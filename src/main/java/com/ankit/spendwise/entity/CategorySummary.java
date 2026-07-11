package com.ankit.spendwise.entity;

public class CategorySummary {
    private String category;
    private Double totalAmount;

    public CategorySummary() {

    }

    public CategorySummary(String category, Double totalAmount) {
        this.category=category;
        this.totalAmount=totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount=totalAmount;
    }
}
