package controller;
import view.ExpenseTrackerView;
import java.util.List;
import model.ExpenseTrackerModel;
import model.Transaction;

/**
 * The ExpenseTrackerController class is responsible for managing the interactions between the model and the view.
 * This class handles user interactions like adding transactions, refreshing the transaction table, and applying any filters the user specifies.
 */
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  /**
   * The constructor method that initializes the model and view components.
   * 
   * @param model the model that contains the transaction data
   * @param view the view that displays the transaction data
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  /**
   * Retrieves the latest transactions from the model and uses that list to update the view.
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * Validates the input data (the amount and category that the user specifies), then creates a new Transaction object if the data is valid. Then, this method adds the new Transaction object to the model. Finally, the view is refreshed and the table is updated.
   * 
   * @param amount the amount of the new transaction that the user inputted
   * @param category the category of the new transaction that the user inputted
   * @return true if the transaction was successfully added, false otherwise
   */
  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods

  /**
   * Applies the user-specified filter to the list of transactions and updates the view to show the filtered list of transactions.
   * 
   * @param filter the filter to apply (either amount or category)
   */
  public void applyFilter(TransactionFilter filter) {
    List<Transaction> transactions = model.getTransactions();
    view.refreshTable(filter.filter(transactions));
  }
}