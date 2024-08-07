package com.tiny.bank.transaction.service;

import com.tiny.bank.exception.BadRequestException;
import com.tiny.bank.exception.PaymentRequiredException;
import com.tiny.bank.transaction.model.Transaction;
import com.tiny.bank.transaction.repository.TransactionRepository;
import com.tiny.bank.user.model.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    public TransactionService() {}

    public void deposit(@NonNull User user, double amount) {
        double beforeBalance = user.getBalance();
        double afterBalance = beforeBalance + amount;
        user.setBalance(afterBalance);
        TransactionRepository.getInstance().save(
            new Transaction(
                Transaction.Type.DEPOSIT,
                amount,
                new Transaction.User(user.getName(), beforeBalance, afterBalance),
                null,
                null
        ));
    }

    public void withdraw(@NonNull User user, double amount) {
        if (user.getBalance() < amount) {
            throw new PaymentRequiredException("Insufficient balance");
        }
        double beforeBalance = user.getBalance();
        double afterBalance = beforeBalance - amount;
        user.setBalance(afterBalance);
        TransactionRepository.getInstance().save(
            new Transaction(
                Transaction.Type.WITHDRAW,
                amount,
                new Transaction.User(user.getName(), beforeBalance, afterBalance),
                null,
                null
        ));
    }

    public void transfer(@NonNull User fromUser, @NonNull User toUser, double amount) {
        if (!fromUser.isActive() || !toUser.isActive()) {
            throw new BadRequestException("Both users must be active");
        }

        if (amount <= 0) {
            throw new BadRequestException("Amount must be greater than 0");
        }

        if (fromUser.getName().equals(toUser.getName())) {
            throw new BadRequestException("Cannot transfer to the same account");
        }

        if (fromUser.getBalance() < amount) {
            throw new PaymentRequiredException("Insufficient balance");
        }

        double beforeBalanceFrom = fromUser.getBalance();
        double afterBalanceFrom = beforeBalanceFrom - amount;
        fromUser.setBalance(afterBalanceFrom);

        double beforeBalanceTo = toUser.getBalance();
        double afterBalanceTo = beforeBalanceTo + amount;
        toUser.setBalance(afterBalanceTo);

        TransactionRepository.getInstance().save(
            new Transaction(
                Transaction.Type.TRANSFER,
                amount,
                null,
                new Transaction.User(fromUser.getName(), beforeBalanceFrom, afterBalanceFrom),
                new Transaction.User(toUser.getName(), beforeBalanceTo, afterBalanceTo)
        ));
    }

    public List<Transaction> getAll() {
        return TransactionRepository.getInstance().getAll();
    }
}
