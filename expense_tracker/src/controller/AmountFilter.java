package controller;

import model.Transaction;
import java.util.List;

public class AmountFilter implements TransactionFilter {
    private final double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return transactions.stream().filter(e -> e.getAmount() == amount).toList();
    }
}
