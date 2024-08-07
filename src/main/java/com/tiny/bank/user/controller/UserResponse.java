package com.tiny.bank.user.controller;

public class UserResponse {

    public record NestedUserResponse(String name, boolean active) {}

    private final NestedUserResponse user;

    public UserResponse(String name, boolean active) {
        this.user = new NestedUserResponse(name, active);
    }

    public NestedUserResponse getUser() {
        return this.user;
    }
}



