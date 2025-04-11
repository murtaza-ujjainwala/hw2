import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

import controller.TransactionFilter;
import controller.AmountFilter;
import controller.CategoryFilter;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // filter button event handler
    view.getFilterBtn().addActionListener(e -> {
      double filterAmount = view.getFilterAmountField();
      String filterCategory = view.getFilterCategoryField();

      if (!filterCategory.isEmpty()) {
        if (!InputValidation.isValidCategory(filterCategory)) {
          JOptionPane.showMessageDialog(view, "Invalid category entered");
          view.toFront();
        }
        TransactionFilter categoryFilter = new CategoryFilter(filterCategory);
        controller.applyFilter(categoryFilter);
      }
      
      else if (filterAmount > 0) {
        if (!InputValidation.isValidAmount(filterAmount)) {
          JOptionPane.showMessageDialog(view, "Invalid amount entered");
          view.toFront();
        }
        TransactionFilter amountFilter = new AmountFilter(filterAmount);
        controller.applyFilter(amountFilter);
      }

      else {
        JOptionPane.showMessageDialog(view, "Please enter a valid filter");
      }
    });
  }
}