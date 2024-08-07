package com.tiny.bank.transaction.repository;

import com.tiny.bank.transaction.model.Transaction;
import org.springframework.lang.NonNull;

import java.util.LinkedList;
import java.util.List;

public class TransactionRepository {

    private final static LinkedList<Transaction> TRANSACTIONS = new LinkedList<>();

    private static TransactionRepository instance = null;

    private TransactionRepository() {}

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }
        return instance;
    }

    public List<Transaction> getAll() {
        return TRANSACTIONS;
    }

    public Transaction save(@NonNull Transaction transaction) {
        TRANSACTIONS.addFirst(transaction);
        return transaction;
    }
}
