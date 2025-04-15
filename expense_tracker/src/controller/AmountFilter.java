package controller;

import model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The AmountFilter class implements the TransactionFilter interface and overrides the filter method defined there.
 * This class is used to find and return all existing transactions that match the user-specified amount.
 */
public class AmountFilter implements TransactionFilter {
    private final double amount;

    /**
     * The constructor method that initializes the AmountFilter instance with the user-specified amount.
     * 
     * @param amount the amount to filter the existing transactions list by
     */
    public AmountFilter(double amount) {
        this.amount = amount;
    }

    /**
     * Filters the list of transactions, returning only the Transaction objects in the list that match the amount specified by the user.
     * 
     * @param transactions the list of transactions to filter on
     * @return a list of transactions that match the specified amount
     */
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(e -> e.getAmount() == amount).collect(Collectors.toList());
    }
}
