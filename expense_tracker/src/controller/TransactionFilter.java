package controller;

import java.util.List;
import model.Transaction;

/**
 * The TransactionFilter interface defines the method signature for filtering transactions.
 * This method will be overridden in any classes that inherit this interface.
 */
public interface TransactionFilter {

    /**
     * Filters the list of transactions based on the arguments passed into the method.
     * 
     * @param transactions the list of transactions to be filtered
     * @return a filtered list of transactions that match the criteria
     */
    List<Transaction> filter(List<Transaction> transactions);
}
