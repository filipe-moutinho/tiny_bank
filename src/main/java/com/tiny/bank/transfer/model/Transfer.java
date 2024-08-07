package com.tiny.bank.transfer.model;

public record Transfer(String fromUsername, String toUsername, double amount) {}
