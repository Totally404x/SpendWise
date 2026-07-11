package com.ankit.spendwise.service;
import com.ankit.spendwise.entity.DashboardSummary;
import com.ankit.spendwise.entity.Expense;
import com.ankit.spendwise.entity.CategorySummary;
import com.ankit.spendwise.repository.ExpenseRepository;

import java.util.List;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final CategoryService categoryService;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(CategoryService categoryService) {
        this.expenseRepository= new ExpenseRepository();
        this.categoryService=categoryService;
    }

    public void saveExpense(Expense expense) {
        String category= categoryService.getCategory(expense.getNote());
        System.out.println(category);
        expense.setCategory(category);
        expenseRepository.saveExpense(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
    }

    public Map<String, List<Expense>> groupExpensesByCategory() {
        List<Expense> expenses= expenseRepository.groupExpensesByCategory();
        Map<String, List<Expense>> groupedExpenses= new LinkedHashMap<>();
        for(Expense expense: expenses) {
            groupedExpenses.computeIfAbsent(expense.getCategory(), k -> new ArrayList<>()).add(expense);
        }
        return groupedExpenses;
    }


    public Expense getExpenseById(Long id) {
        return expenseRepository.getExpenseById(id);
    }

    public List<Expense> getExpenseByAmount(Double amount) {
        return expenseRepository.getExpenseByAmount(amount);
    }

    public List<Expense> getExpenseByNote(String note) {
        return expenseRepository.getExpenseByNote(note);
    }

    public List<Expense> sortExpensesByAmountLowToHigh() {
        return expenseRepository.sortExpensesByAmountLowToHigh();
    }

    public List<Expense> sortExpensesByAmountHighToLow() {
        return expenseRepository.sortExpensesByAmountHighToLow();
    }

    public DashboardSummary getDashboard() {
        return expenseRepository.getDashboard();
    }

    public DashboardSummary getMonthlyDashboard(Integer month, Integer year) {
        return expenseRepository.getMonthlyDashboard(month,year);
    }

    public List<CategorySummary> getCategoryWiseSummary() {
        return expenseRepository.getCategoryWiseSummary();
    }

    public boolean updateExpense(Expense expense) {
       return expenseRepository.updateExpense(expense);
    }

    public boolean deleteExpense(Long id) {
        return expenseRepository.deleteExpense(id);
    }
}
