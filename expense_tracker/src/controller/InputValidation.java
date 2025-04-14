package controller;

import java.util.Arrays;

/**
 * Class that handles the validation of the inputs. The amount field should be a number between 0 and 1000, and the category field should either be "food", "travel", "bills", "entertainment", or "other".
 */
public class InputValidation {

  /**
   * Validates the amount field. This number should be between 0 and 1000.
   * @param amount_str The user's input of the amount.
   * @return The amount if it is valid.
   */
  public static boolean isValidAmount(double amount) {
    
    // Check range
    if(amount > 1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
  }

  /**
   * Validates the category field. This value should be "food", "travel", "bills", "entertainment", or "other".
   * @param category_str The user's input of the category.
   * @return The category if it is valid.
   */
  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}