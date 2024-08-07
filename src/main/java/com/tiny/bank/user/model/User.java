package com.tiny.bank.user.model;

public class User {
    private final String name;
    private double balance;
    private boolean active;

    public User(String name) {
        this.name = name;
        this.active = true;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
