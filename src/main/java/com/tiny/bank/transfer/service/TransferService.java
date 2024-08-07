package com.tiny.bank.transfer.service;

import com.tiny.bank.exception.ResourceNotFoundException;
import com.tiny.bank.transaction.service.TransactionService;
import com.tiny.bank.transfer.model.Transfer;
import com.tiny.bank.user.model.User;
import com.tiny.bank.user.service.UserService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private final UserService userService;
    private final TransactionService transactionService;

    public TransferService(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public void create(@NonNull Transfer transfer) {
        User fromUser = this.userService.get(transfer.fromUsername());
        if (fromUser == null) {
            throw new ResourceNotFoundException("fromUser not found");
        }
        User toUser = this.userService.get(transfer.toUsername());
        if (toUser == null) {
            throw new ResourceNotFoundException("toUser not found");
        }
        this.transactionService.transfer(fromUser, toUser, transfer.amount());
    }
}
