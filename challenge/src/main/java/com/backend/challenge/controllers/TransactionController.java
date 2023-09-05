package com.backend.challenge.controllers;

import com.backend.challenge.DTO.TransactionDTO;
import com.backend.challenge.domain.transaction.Transaction;
import com.backend.challenge.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService service;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        Transaction newTransaction = this.service.createTransaction(transaction);

        return new ResponseEntity<>(newTransaction, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions(){
        List<Transaction> transactions = this.service.getAllTransactions();
        return ResponseEntity.status(200).body(transactions);
    }
}
