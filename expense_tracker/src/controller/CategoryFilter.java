package controller;

import model.Transaction;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CategoryFilter class implements the TransactionFilter interface and overrides the filter method defined there.
 * This class is used to find and return all existing transactions that match the user-specified category.
 */
public class CategoryFilter implements TransactionFilter {
    private final String category;

    /**
     * The constructor method that initializes the CategoryFilter instance with the user-specified category.
     * 
     * @param category the category to filter the existing transactions list by
     */
    public CategoryFilter(String category) {
        this.category = category;
    }

    /**
     * Filters the list of transactions, returning only the Transaction objects in the list that match the category specified by the user.
     * 
     * @param transactions the list of transactions to filter on
     * @return a list of transactions that match the specified category
     */
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(e -> e.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
    }
}
