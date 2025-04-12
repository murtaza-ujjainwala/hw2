// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.AmountFilter;
import controller.CategoryFilter;
import controller.ExpenseTrackerController;
import controller.InputValidation;
import controller.TransactionFilter;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;


public class TestExample {

    private ExpenseTrackerModel model;
    private ExpenseTrackerView view;
    private ExpenseTrackerController controller;

    @Before
    public void setup() {
        model = new ExpenseTrackerModel();
        view = new ExpenseTrackerView();
        controller = new ExpenseTrackerController(model, view);
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }


    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);
    
        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
    public void testAddTransaction2() {
        assertEquals(0, model.getTransactions().size());

        assertTrue(controller.addTransaction(50.00, "food"));

        assertEquals(1, model.getTransactions().size());

        assertEquals(50.00, model.getTransactions().get(0).getAmount(), 0.01);

        assertEquals(50.00, getTotalCost(), 0.01);
    }

    @Test
    public void testInvalidInputHandling() {
        assertTrue("Category can't be null", !InputValidation.isValidCategory(""));

        assertTrue("Invalid category", !InputValidation.isValidCategory("groceries"));

        assertTrue("Category can't be zero", !InputValidation.isValidAmount(0));

        assertTrue("Category must be positive", !InputValidation.isValidAmount(-1.26));

        assertTrue("Category must be less than or equal to 1000", !InputValidation.isValidAmount(1000.01));

        assertTrue(model.getTransactions().isEmpty());

        assertEquals(0, getTotalCost(), 0.0000001);
    }

    @Test
    public void testFilterByAmount() {
        controller.addTransaction(50.00, "bills");
        controller.addTransaction(20.00, "food");
        controller.addTransaction(35.56, "travel");
        controller.addTransaction(35.56, "other");

        assertEquals(4, model.getTransactions().size());

        TransactionFilter amountFilter = new AmountFilter(35.56);
        List<Transaction> filteredList = amountFilter.filter(model.getTransactions());
        assertEquals(2, filteredList.size());
        assertTrue(filteredList.stream().allMatch(e -> e.getAmount() == 35.56));
    }

    @Test
    public void testFilterByCategory() {
        controller.addTransaction(50.00, "bills");
        controller.addTransaction(20.00, "bills");
        controller.addTransaction(35.56, "travel");
        controller.addTransaction(35.56, "food");

        assertEquals(4, model.getTransactions().size());

        TransactionFilter categoryFilter = new CategoryFilter("bills");
        List<Transaction> filteredList = categoryFilter.filter(model.getTransactions());
        assertEquals(2, filteredList.size());
        assertTrue(filteredList.stream().allMatch(e -> e.getCategory().equalsIgnoreCase("bills")));
    }
}