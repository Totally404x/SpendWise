package com.ankit.spendwise.controller;
import com.ankit.spendwise.entity.DashboardSummary;
import com.ankit.spendwise.entity.Expense;
import com.ankit.spendwise.entity.CategorySummary;
import com.ankit.spendwise.service.ExpenseService;
import com.ankit.spendwise.service.CategoryService;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final CategoryService categoryService;

    public ExpenseController(ExpenseService expenseService,
                             CategoryService categoryService) {
        this.expenseService = expenseService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public void saveExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
    }

    @GetMapping
    public List<Expense> getAllExpense() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/group-by-category")
    public Map<String, List<Expense>> groupExpensesByCategory() {
        return expenseService.groupExpensesByCategory();
    }

    @GetMapping("/search")
    public List<Expense> getExpenseByNote(@RequestParam String note) {
        return expenseService.getExpenseByNote(note);
    }

    @GetMapping("/id/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/amount")
    public List<Expense> getExpenseByAmount(@RequestParam Double amount) {
        return expenseService.getExpenseByAmount(amount);
    }

    @GetMapping("/sort/low-high")
    public List<Expense> sortExpensesByAmountLowToHigh() {
        return expenseService.sortExpensesByAmountLowToHigh();
    }

    @GetMapping("/sort/high-low")
    public List<Expense> sortExpensesByAmountHighToLow() {
        return expenseService.sortExpensesByAmountHighToLow();
    }

    @GetMapping("/dashboard")
    public DashboardSummary getDashboard() {
        return expenseService.getDashboard();
    }

    @GetMapping("/monthly-dashboard")
    public DashboardSummary getMonthlyDashboard(@RequestParam Integer month, @RequestParam Integer year) {
        return expenseService.getMonthlyDashboard(month,year);
    }

    @GetMapping("/category-wise-summary")
    public List<CategorySummary> getCategoryWiseSummary() {
        return expenseService.getCategoryWiseSummary();
    }

    @PostMapping("/update/{id}")
    public boolean updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id);
        return expenseService.updateExpense(expense);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteExpense(@PathVariable Long id) {
        return expenseService.deleteExpense(id);
    }
}
