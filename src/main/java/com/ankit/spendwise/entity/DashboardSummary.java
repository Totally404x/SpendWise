package com.ankit.spendwise.entity;

public class DashboardSummary {
    private Double totalSpend;
    private Double highestSpend;
    private String highestCategory;
    private Integer numberOfTransactions;

    public DashboardSummary() {

    }

    public void DashboardSummary(Double totalSpend, Double highestSpend,  String highestCategory, Integer numberOfTransactions) {
        this.totalSpend=totalSpend;
        this.highestSpend=highestSpend;
        this.highestCategory=highestCategory;
        this.numberOfTransactions=numberOfTransactions;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public Double getHighestSpend() {
        return highestSpend;
    }

    public String getHighestCategory() {
        return highestCategory;
    }

    public Integer getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend=totalSpend;
    }

    public void setHighestSpend(Double highestSpend) {
        this.highestSpend=highestSpend;
    }

    public void setHighestCategory(String highestCategory) {
        this.highestCategory=highestCategory;
    }

    public void setNumberOfTransactions(Integer numberOfTransactions) {
        this.numberOfTransactions=numberOfTransactions;
    }

}
