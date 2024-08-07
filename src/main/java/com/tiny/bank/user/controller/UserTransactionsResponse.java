package com.tiny.bank.user.controller;

import com.tiny.bank.transaction.model.Transaction;

import java.util.List;

public class UserTransactionsResponse {

    public record NestedUserTransactionsResponse(List<Transaction> Transactions) {}

    private final NestedUserTransactionsResponse userTransactions;

    public UserTransactionsResponse(List<Transaction> transactions) {
        this.userTransactions = new NestedUserTransactionsResponse(transactions);
    }

    public NestedUserTransactionsResponse getUserTransactions() {
        return this.userTransactions;
    }
}
