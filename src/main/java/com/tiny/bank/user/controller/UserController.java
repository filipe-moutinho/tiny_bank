package com.tiny.bank.user.controller;

import com.tiny.bank.user.model.User;
import com.tiny.bank.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<UserResponse> get(@PathVariable String name) {
        User user = this.userService.get(name);
        return new ResponseEntity<>(new UserResponse(user.getName(), user.isActive()), HttpStatus.OK);
    }

    @PostMapping("/{name}")
    public ResponseEntity<UserResponse> post(@PathVariable String name) {
        User user = this.userService.create(name);
        return new ResponseEntity<>(new UserResponse(user.getName(), user.isActive()), HttpStatus.CREATED);
    }

    @PatchMapping("/{name}/deactivate")
    public ResponseEntity<UserResponse> patchDeactivate(@PathVariable String name) {
        User user = this.userService.deactivateUser(name);
        return new ResponseEntity<>(new UserResponse(user.getName(), user.isActive()), HttpStatus.OK);
    }

    @PatchMapping("/{name}/deposit/{amount}")
    public ResponseEntity<Void> patchDeposit(@PathVariable String name, @PathVariable double amount) {
        this.userService.deposit(name, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{name}/withdraw/{amount}")
    public ResponseEntity<Void> patchWithdraw(@PathVariable String name, @PathVariable double amount) {
        this.userService.withdraw(name, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{name}/balance")
    public ResponseEntity<UserBalanceResponse> getBalance(@PathVariable String name) {
        double balance = this.userService.getBalance(name);
        return new ResponseEntity<>(new UserBalanceResponse(balance), HttpStatus.OK);
    }
}
