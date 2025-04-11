package controller;

import model.Transaction;
import java.util.List;

public class CategoryFilter implements TransactionFilter {
    private final String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(e -> e.getCategory().equalsIgnoreCase(category)).toList();
    }
}
