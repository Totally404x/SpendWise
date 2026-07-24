package com.ankit.spendwise.service;
import com.ankit.spendwise.entity.DashboardSummary;
import com.ankit.spendwise.entity.Expense;
import com.ankit.spendwise.entity.CategorySummary;
import com.ankit.spendwise.repository.ExpenseRepository;
import com.ankit.spendwise.exception.ExpenseNotFoundException;

import java.time.LocalDate;
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
        List<Expense> expenses= expenseRepository.getAllExpenses();
        if (expenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be found.");
        }

        return expenses;
    }

    public Map<String, List<Expense>> groupExpensesByCategory() {
        List<Expense> expenses= expenseRepository.groupExpensesByCategory();
        Map<String, List<Expense>> groupedExpenses= new LinkedHashMap<>();
        for(Expense expense: expenses) {
            groupedExpenses.computeIfAbsent(expense.getCategory(), k -> new ArrayList<>()).add(expense);
        }
        if (groupedExpenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be categorized.");
        }
        return groupedExpenses;
    }


    public Expense getExpenseById(Long id) {
        Expense expense= expenseRepository.getExpenseById(id);
        if (expense == null) {
            throw new ExpenseNotFoundException("Expense with ID:"+id +" not found.");
        }
        return expense;
    }

    public List<Expense> getExpenseByAmount(Double amount) {
        List<Expense> expenses= expenseRepository.getExpenseByAmount(amount);
        if (expenses == null) {
            throw new ExpenseNotFoundException("No Expenses of amount:"+amount+" were found.");
        }
        return expenses;
    }

    public List<Expense> getExpenseByNote(String note) {
        List<Expense> expenses= expenseRepository.getExpenseByNote(note);
        if (expenses == null) {
            throw new ExpenseNotFoundException("No Expense with note:"+note+" were found.");
        }
        return expenses;
    }

    public List<Expense> sortExpensesByAmountLowToHigh() {
        List<Expense> expenses= expenseRepository.sortExpensesByAmountLowToHigh();
        if (expenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be sorted.");
        }
        return expenses;
    }

    public List<Expense> sortExpensesByAmountHighToLow() {
        List<Expense> expenses= expenseRepository.sortExpensesByAmountHighToLow();
        if (expenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be sorted.");
        }
        return expenses;
    }

    public List<Expense> sortExpensesByCategory() {
        List<Expense> expenses= expenseRepository.sortExpensesByCategory();
        if(expenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be sorted.");
        }
        return expenses;
    }

    public List<Expense> sortExpensesByDate() {
        List<Expense> expenses= expenseRepository.sortExpensesByDate();
        if(expenses == null) {
            throw new ExpenseNotFoundException("Expenses could not be sorted.");
        }
        return expenses;
    }

    public DashboardSummary getDashboard() {
        DashboardSummary summary= expenseRepository.getDashboard();
        if (summary == null) {
            throw new ExpenseNotFoundException("Dashboard not available.");
        }
        return summary;
    }

    public DashboardSummary getMonthlyDashboard(Integer month, Integer year) {
        DashboardSummary summary= expenseRepository.getMonthlyDashboard(month,year);
        if (summary == null) {
            throw new ExpenseNotFoundException("Dashboard for month="+month+", year="+year+"could not be fetched.");
        }
        return summary;
    }

    public DashboardSummary getYearlyDashboard(Integer year) {
        DashboardSummary summary= expenseRepository.getYearlyDashboard(year);
        if (summary == null) {
            throw new ExpenseNotFoundException("Dashboard for year="+year+"could not be fetched.");
        }
        return summary;
    }

    public List<CategorySummary> getTopSpendingCategories() {
        List<CategorySummary> summaries= expenseRepository.getCategoryWiseSummary();
        if (summaries == null) {
            throw new ExpenseNotFoundException("Category summaries could not be fetched.");
        }
        return summaries;
    }

    public List<CategorySummary> getCategoryWiseSummary() {
        List<CategorySummary> summaries= expenseRepository.getCategoryWiseSummary();
        if (summaries == null) {
            throw new ExpenseNotFoundException("Category summaries could not be fetched.");
        }
        return summaries;
    }

    public List<Expense> getExpenseByDateRange(LocalDate from, LocalDate to) {
        List<Expense> expenses= expenseRepository.getExpenseByDateRange(from,to);
        if (expenses == null) {
            throw new ExpenseNotFoundException("Expenses from:"+from+" to:"+to+" not found.");
        }
        return expenses;
    }

    public boolean updateExpense(Expense expense) {
       boolean test= expenseRepository.updateExpense(expense);
        if (test == false) {
            throw new ExpenseNotFoundException("Expense could not be updated.");
        }
        return test;
    }

    public boolean deleteExpense(Long id) {
        boolean test= expenseRepository.deleteExpense(id);
        if (test == false) {
            throw new ExpenseNotFoundException("Expense could not be deleted.");
        }
        return test;
    }
}
