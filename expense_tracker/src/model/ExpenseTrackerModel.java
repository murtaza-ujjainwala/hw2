package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The ExpenseTrackerModel class is responsible for managing the transaction data.
 * This class maintains a list of transactions, providing methods to add, remove, and retrieve transactions.
 */
public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  /**
   * Constructor method that creates a new ExpenseTrackerModel instance with an empty ArrayList that will store transactions.
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  /**
   * Adds a new transaction to the list of transactions.
   * 
   * @param t the Transaction object to be added to the list of transactions
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  /**
   * Removes a specified transaction from the list of transactions.
   * 
   * @param t the Transaction object to be removed from the list of transactions
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  /**
   * Retrieves an immutable copy of the list of transactions.
   * 
   * @return an immutable copy of the list of transactions
   */
  public List<Transaction> getTransactions() {
    return List.copyOf(transactions);
  }

}