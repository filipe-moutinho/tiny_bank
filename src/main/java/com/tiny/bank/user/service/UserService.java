package com.tiny.bank.user.service;

import com.tiny.bank.exception.PaymentRequiredException;
import com.tiny.bank.exception.ResourceNotFoundException;
import com.tiny.bank.transaction.service.TransactionService;
import com.tiny.bank.user.model.User;
import com.tiny.bank.user.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final TransactionService transactionService;

    public UserService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public User get(String name) {
        return UserRepository.getInstance().get(name);
    }

    public User create(@NonNull String name) {
        if (UserRepository.getInstance().get(name) != null) {
            throw new ResourceNotFoundException("User already exists");
        }
        return UserRepository.getInstance().save(new User(name));
    }

    public User deactivateUser(@NonNull String name) {
        User user = UserRepository.getInstance().get(name);
        validateUser(user);
        if (user.getBalance() != 0) {
            throw new PaymentRequiredException("User balance must be 0");
        }
        user.setActive(false);
        return user;
    }

    public void deposit(@NonNull String name, double amount) {
        User user = UserRepository.getInstance().get(name);
        validateUser(user);
        validateAmount(amount);
        this.transactionService.deposit(user, amount);
    }

    public void withdraw(@NonNull String name, double amount) {
        User user = UserRepository.getInstance().get(name);
        validateUser(user);
        validateAmount(amount);
        this.transactionService.withdraw(user, amount);
    }

    public double getBalance(@NonNull String name) {
        User user = UserRepository.getInstance().get(name);
        validateUser(user);
        return user.getBalance();
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        if (!user.isActive()) {
            throw new ResourceNotFoundException("User is not active");
        }
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }
}
