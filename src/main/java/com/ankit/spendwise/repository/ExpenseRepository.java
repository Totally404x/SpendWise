package com.ankit.spendwise.repository;
import com.ankit.spendwise.entity.CategorySummary;
import com.ankit.spendwise.entity.DashboardSummary;
import com.ankit.spendwise.entity.Expense;
import com.ankit.spendwise.config.DatabaseConfig;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ExpenseRepository {

    public void saveExpense(Expense expense) {
        String query="INSERT INTO expenses(amount,note,expensedate,category) VALUES (?,?,?,?)";
        try (
                Connection connection=DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ) {
            preparedStatement.setDouble(1,expense.getAmount());
            preparedStatement.setString(2,expense.getNote());
            preparedStatement.setDate(3,java.sql.Date.valueOf(expense.getDate()));
            System.out.println("Category before insert = " + expense.getCategory());
            preparedStatement.setString(4, expense.getCategory());
            System.out.println("Parameter 4 = " + expense.getCategory());
            preparedStatement.executeUpdate();
            System.out.println("Added Successfully.");
        }
        catch(SQLException E) {
            E.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        String query="SELECT * FROM expenses ORDER BY expensedate DESC";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> groupExpensesByCategory() {
        String query="SELECT * FROM expenses ORDER BY category,expensedate DESC";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public Expense getExpenseById(Long id) {
        String query= "SELECT * FROM expenses WHERE id=?";
        try(
                Connection connection=DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Expense expense= new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate(resultSet.getDate("expensedate").toLocalDate());
                expense.setCategory(resultSet.getString("category"));
                return expense;
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return null;
    }

    public List<Expense> getExpenseByAmount(Double amount) {
        String query="SELECT * FROM expenses WHERE amount=?";
        List<Expense> expenses=new ArrayList<>();
        try(
                Connection connection=DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ) {
            preparedStatement.setDouble(1,amount);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Expense expense=new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate(resultSet.getDate("expensedate").toLocalDate());
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        } catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> getExpenseByNote(String note) {
        String query= "SELECT * FROM expenses WHERE note LIKE ?";
        List<Expense> expenses=new ArrayList<>();
        try(
                Connection connection=DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1,"%"+note+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                Expense expense= new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate(resultSet.getDate("expensedate").toLocalDate());
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> sortExpensesByAmountLowToHigh() {
        String query="SELECT * FROM expenses ORDER BY amount ASC";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> sortExpensesByAmountHighToLow() {
        String query="SELECT * FROM expenses ORDER BY amount DESC";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> sortExpensesByCategory() {
        String query="SELECT * FROM expenses ORDER BY category";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public List<Expense> sortExpensesByDate() {
        String query="SELECT * FROM expenses ORDER BY expensedate ASC";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                ResultSet resultSet= preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public DashboardSummary getDashboard() {
        String query1 = "SELECT SUM(amount) AS totalSpend, MAX(amount) AS highestSpend, COUNT(*) AS numberOfTransactions FROM expenses ";
        DashboardSummary summary = new DashboardSummary();
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query1);
        ) {
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setTotalSpend(resultSet.getDouble("totalSpend"));
                summary.setHighestSpend(resultSet.getDouble("highestSpend"));
                summary.setNumberOfTransactions(resultSet.getInt("numberOfTransactions"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        String query2 = "SELECT category FROM expenses GROUP BY category ORDER BY SUM(amount) DESC LIMIT 1";
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query2);
        ) {
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setHighestCategory(resultSet.getString("category"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        return summary;
    }

    public DashboardSummary getMonthlyDashboard(Integer month, Integer year) {
        String query1 = "SELECT SUM(amount) AS totalSpend, MAX(amount) AS highestSpend, COUNT(*) AS numberOfTransactions FROM expenses WHERE MONTH(expensedate)=? AND YEAR(expensedate)=?";
        DashboardSummary summary = new DashboardSummary();
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query1);
        ) {
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setTotalSpend(resultSet.getDouble("totalSpend"));
                summary.setHighestSpend(resultSet.getDouble("highestSpend"));
                summary.setNumberOfTransactions(resultSet.getInt("numberOfTransactions"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        String query2 = "SELECT category FROM expenses WHERE MONTH(expensedate)=? AND YEAR(expensedate)=? GROUP BY category ORDER BY SUM(amount) DESC LIMIT 1";
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query2);
        ) {
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setHighestCategory(resultSet.getString("category"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        return summary;
    }

    public List<CategorySummary> getCategoryWiseSummary() {
        String query="SELECT category, SUM(amount) as totalAmount FROM expenses GROUP BY category";
        List<CategorySummary> summaries=new ArrayList<>();
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                CategorySummary summary = new CategorySummary();
                summary.setCategory(resultSet.getString("category"));
                summary.setTotalAmount(resultSet.getDouble("totalAmount"));
                summaries.add(summary);
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        return summaries;
    }

    public List<CategorySummary> getTopSpendingCategories() {
        String query="SELECT category, SUM(amount) as totalAmount FROM expenses GROUP BY category ORDER BY totalSpent DESC";
        List<CategorySummary> summaries=new ArrayList<>();
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
        ) {
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                CategorySummary summary = new CategorySummary();
                summary.setCategory(resultSet.getString("category"));
                summary.setTotalAmount(resultSet.getDouble("totalAmount"));
                summaries.add(summary);
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        return summaries;
    }

    public DashboardSummary getYearlyDashboard(Integer year) {
        String query1 = "SELECT SUM(amount) AS totalSpend, MAX(amount) AS highestSpend, COUNT(*) AS numberOfTransactions FROM expenses WHERE YEAR(expensedate)=?";
        DashboardSummary summary = new DashboardSummary();
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query1);
        ) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setTotalSpend(resultSet.getDouble("totalSpend"));
                summary.setHighestSpend(resultSet.getDouble("highestSpend"));
                summary.setNumberOfTransactions(resultSet.getInt("numberOfTransactions"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        String query2 = "SELECT category FROM expenses WHERE YEAR(expensedate)=? GROUP BY category ORDER BY SUM(amount) DESC LIMIT 1";
        try(
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query2);
        ) {
            preparedStatement.setInt(1, year);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                summary.setHighestCategory(resultSet.getString("category"));
            }
        } catch(SQLException E) {
            E.printStackTrace();
        }
        return summary;
    }

        public List<Expense> getExpenseByDateRange(LocalDate from, LocalDate to) {
        String query="SELECT * FROM expenses WHERE expensedate BETWEEN ? and ?";
        List<Expense> expenses= new ArrayList<>();
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(query);
        ) {
            preparedStatement.setDate(1,java.sql.Date.valueOf(from));
            preparedStatement.setDate(2,java.sql.Date.valueOf(to));
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()) {
                Expense expense = new Expense();
                expense.setId(resultSet.getLong("id"));
                expense.setAmount(resultSet.getDouble("amount"));
                expense.setNote(resultSet.getString("note"));
                expense.setDate((resultSet.getDate("expensedate").toLocalDate()));
                expense.setCategory(resultSet.getString("category"));
                expenses.add(expense);
            }
        }
        catch (SQLException E) {
            E.printStackTrace();
        }
        return expenses;
    }

    public boolean updateExpense(Expense expense) {
        String query="UPDATE expenses SET amount=?, note=?, category=? WHERE id=?";
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
                ) {
            preparedStatement.setDouble(1,expense.getAmount());
            preparedStatement.setString(2,expense.getNote());
            preparedStatement.setString(3,expense.getCategory());
            preparedStatement.setLong(4,expense.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException E) {
            E.printStackTrace();
        }
        return false;
    }

    public boolean deleteExpense(Long id) {
        String query="DELETE FROM expenses WHERE id=?";
        try (
                Connection connection= DatabaseConfig.getConnection();
                PreparedStatement preparedStatement= connection.prepareStatement(query);
        ) {
            preparedStatement.setDouble(1,id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected>0;
        }
        catch(SQLException E) {
            E.printStackTrace();
        }
        return false;
    }
}
