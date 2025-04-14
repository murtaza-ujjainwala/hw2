package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import controller.TransactionFilter;
import controller.CategoryFilter;
import controller.AmountFilter;

import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

import model.Transaction;
import java.util.List;

/**
 * The ExpenseTrackerView class creates the User Interface for the Expense Tracker application.
 * It provides the methods for the input fields (used for entering transactions), the table displaying transaction history, and the button for adding transactions.
 */
public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JButton applyFilterBtn;
  private JFormattedTextField filterAmountField;
  private JTextField filterCategoryField;
  
  /**
   * Constructs the main GUI for the Expense Tracker application.
   */
  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    filterCategoryField = new JTextField(10);
    filterCategoryField.setMaximumSize(new Dimension(150, 20));
    filterAmountField = new JFormattedTextField(format);
    filterAmountField.setMaximumSize(new Dimension(150, 20));
    applyFilterBtn = new JButton("Apply Filter");

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    // Filter components
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
    filterPanel.add(new JLabel("Category Filter:"));
    filterPanel.add(filterCategoryField);
    filterPanel.add(new JLabel("Amount Filter:"));
    filterPanel.add(filterAmountField);
    filterPanel.add(applyFilterBtn);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(filterPanel, BorderLayout.EAST);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(700, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  
  }

  /**
   * Refreshes the transaction table with the latest list of transactions.
   * 
   * @param transactions the list of transactions to display in the table
   */
  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }

  /**
   * Returns the "Add Transaction" button component.
   * 
   * @return the "Add Transaction" JButton
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  /**
   * Returns the "Apply Filter" button component.
   * 
   * @return the "Apply Filter" JButton
   */
  public JButton getFilterBtn() {
    return applyFilterBtn;
  }

  /**
   * Returns the table model used for displaying transaction data in the table.
   * 
   * @return the DefaultTableModel that represents the transaction table
   */
  public DefaultTableModel getTableModel() {
    return model;
  }

  // Other view methods

  /**
   * Returns the JTable component that displays the transactions list.
   * 
   * @return the JTable displaying the transactions
   */
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * Retrieves the amount entered in the amount filter field.
   * 
   * @return the amount that the transactions list will be filtered on, parsed as a double (or 0 if the field is empty)
   */
  public double getFilterAmountField() {
    String text = filterAmountField.getText().replace(",", "").trim();
    if (text.isEmpty()) {
      return 0;
    }
    return Double.parseDouble(text);
  }

  /**
   * Retrieves the category entered in the category filter field.
   * 
   * @return the category that the transactions list will be filtered on, parsed as a String
   */
  public String getFilterCategoryField() {
    return filterCategoryField.getText();
  }

  /**
   * Retrieves the amount entered in the amount field.
   * 
   * @return the amount of the new transaction, parsed as a double (or 0 if the field is empty)
   */
  public double getAmountField() {
    String text = amountField.getText().replace(",", "").trim();
    if (text.isEmpty()) {
      return 0;
    }
    return Double.parseDouble(text);
  }

  /**
   * Sets the amount input field in the view.
   * 
   * @param amountField the JFormattedTextField which will be set as the new amount in the amount input field
   */
  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  /**
   * Retrieves the current text entered in the category input field.
   * 
   * @return the category entered by the user, parsed as a String
   */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /**
   * Sets the category input field in the view.
   * 
   * @param categoryField the JTextField which will be set as the new category in the category input field
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
