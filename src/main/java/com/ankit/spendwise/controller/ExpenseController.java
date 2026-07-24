package com.ankit.spendwise.controller;
import com.ankit.spendwise.entity.DashboardSummary;
import com.ankit.spendwise.entity.Expense;
import com.ankit.spendwise.entity.CategorySummary;
import com.ankit.spendwise.service.ExpenseService;
import com.ankit.spendwise.service.CategoryService;

import java.time.LocalDate;
import java.util.*;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
        return ResponseEntity.ok("Expense has successfully been added.");
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpense() {
    List<Expense> expenses =expenseService.getAllExpenses();
    return ResponseEntity.ok(expenses);
    }

    @GetMapping("/group-by-category")
    public ResponseEntity<Map<String, List<Expense>>> groupExpensesByCategory() {
        Map<String, List<Expense>> map= expenseService.groupExpensesByCategory();
        return ResponseEntity.ok(map);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Expense>> getExpenseByNote(@RequestParam String note) {
        List<Expense> expenses= expenseService.getExpenseByNote(note);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense= expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/amount")
    public ResponseEntity<List<Expense>> getExpenseByAmount(@RequestParam Double amount) {
        List<Expense> expenses= expenseService.getExpenseByAmount(amount);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/sort/low-high")
    public ResponseEntity<List<Expense>> sortExpensesByAmountLowToHigh() {
        List<Expense> expenses= expenseService.sortExpensesByAmountLowToHigh();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/sort/high-low")
    public ResponseEntity<List<Expense>> sortExpensesByAmountHighToLow() {
        List<Expense> expenses= expenseService.sortExpensesByAmountHighToLow();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/sort-by-category")
    public ResponseEntity<List<Expense>> sortExpensesByCategory() {
        List<Expense> expenses= expenseService.sortExpensesByCategory();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/sort/date/old-new")
    public ResponseEntity<List<Expense>> sortExpensesByDate() {
        List<Expense> expenses= expenseService.sortExpensesByDate();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardSummary> getDashboard() {
        DashboardSummary summary=expenseService.getDashboard();
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/monthly-dashboard")
    public ResponseEntity<DashboardSummary> getMonthlyDashboard(@RequestParam Integer month, @RequestParam Integer year) {
        DashboardSummary summary= expenseService.getMonthlyDashboard(month,year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/yearly-dashboard")
    public ResponseEntity<DashboardSummary> getYearlyDashboard(@RequestParam Integer year) {
        DashboardSummary summary= expenseService.getYearlyDashboard(year);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/top-categories")
    public ResponseEntity<List<CategorySummary>> getTopSpendingCategories() {
        List<CategorySummary> summaries= expenseService.getTopSpendingCategories();
        return ResponseEntity.ok(summaries);
    }

    @GetMapping("/category-wise-summary")
    public ResponseEntity<List<CategorySummary>> getCategoryWiseSummary() {
        List<CategorySummary> summaries= expenseService.getCategoryWiseSummary();
        return ResponseEntity.ok(summaries);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpenseByDateRange(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        List<Expense> expenses= expenseService.getExpenseByDateRange(from,to);
        return ResponseEntity.ok(expenses);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id);
        expenseService.updateExpense(expense);
        return ResponseEntity.ok("Expense (id:"+id+") has successfully been updated.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense (id:"+id+") has been deleted.");
    }
}
