package com.tiny.bank.user.controller;

public class UserBalanceResponse {

    public record NestedUserBalanceResponse(double value) {}

    private final NestedUserBalanceResponse userBalance;

    public UserBalanceResponse(double balance) {
        this.userBalance = new NestedUserBalanceResponse(balance);
    }

    public NestedUserBalanceResponse getUserBalance() {
        return this.userBalance;
    }
}
