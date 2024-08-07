package com.tiny.bank.transaction.model;

import java.time.LocalDateTime;

public class Transaction {

    public record User(String name, double beforeBalance, double afterBalance) {}

    public enum Type
    {
        DEPOSIT("DEPOSIT"),
        WITHDRAW("WITHDRAW"),
        TRANSFER("TRANSFER");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private final Type type;
    private final LocalDateTime timestamp;
    private final double amount;
    private final User user;
    private final User fromUser;
    private final User toUser;

    public Transaction(
        Type type,
        double amount,
        User user,
        User fromUser,
        User toUser
    ) {
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.amount = amount;
        this.user = user;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }
}
