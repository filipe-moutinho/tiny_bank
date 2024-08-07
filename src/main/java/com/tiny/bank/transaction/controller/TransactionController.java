package com.tiny.bank.transaction.controller;

import com.tiny.bank.transaction.model.Transaction;
import com.tiny.bank.transaction.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<TransactionResponse> getAll() {
        List<Transaction> transactions = this.transactionService.getAll();
        return new ResponseEntity<>(new TransactionResponse(transactions), HttpStatus.CREATED);
    }
}
