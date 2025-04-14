package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The Transaction class represents a user's transaction.
 * A Transaction object stores the amount of the transaction, the category of the transaction, and the time the transaction was recorded. 
 */
public class Transaction {

  private final double amount;
  private final String category;
  private final String timestamp;

  /**
   * Constructor method for initializing a new Transaction object.
   * @param amount The transaction amount.
   * @param category The transaction category.
   */
  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * Gets the transaction amount.
   * @return The transaction amount.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Gets the transaction category.
   * @return The transaction category.
   */
  public String getCategory() {
    return category;
  }

  /**
   * Gets the timestamp of when the transaction was created by the user.
   * @return The timestamp of the transaction.
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Generates the timestamp for the transaction.
   * @return The current date and time in dd-MM-yyyy HH:mm format.
   */
  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}