package com.tiny.bank.transfer.controller;

import com.tiny.bank.transfer.model.Transfer;
import com.tiny.bank.transfer.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> post(@RequestBody Transfer transfer) {

        this.transferService.create(transfer);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
