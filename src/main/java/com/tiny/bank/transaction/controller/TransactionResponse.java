package com.tiny.bank.transaction.controller;

import com.tiny.bank.transaction.model.Transaction;

import java.util.List;

public class TransactionResponse {

    private final List<Transaction> transactions;

    public TransactionResponse(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }
}
