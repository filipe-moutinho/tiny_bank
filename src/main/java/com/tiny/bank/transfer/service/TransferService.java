package com.tiny.bank.transfer.service;

import com.tiny.bank.exception.BadRequestException;
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
        validateUser(fromUser);
        User toUser = this.userService.get(transfer.toUsername());
        validateUser(toUser);
        validateAmount(transfer.amount());
        this.transactionService.transfer(fromUser, toUser, transfer.amount());
    }

    private static void validateUser(User user) {
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        if (!user.isActive()) {
            throw new BadRequestException("User is not active");
        }
    }

    private static void validateAmount(double amount) {
        if (amount <= 0) {
            throw new BadRequestException("Amount must be positive");
        }
    }
}
