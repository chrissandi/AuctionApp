package com.example.AuctionApp.controller;

import com.example.AuctionApp.model.Transaction;
import com.example.AuctionApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Transaction>> getAllTransaction(){
        List<Transaction> transactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id")Long id){
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/getHighestBid/{item_id}")
    public ResponseEntity<Transaction> getHighestBid(@PathVariable("item_id")Long id){
        Transaction transaction = transactionService.getHighestBid(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/getLowestUniqueBid/{item_id}")
    public ResponseEntity<Transaction> getLowestUniquebBid(@PathVariable("item_id")Long id){
        Transaction transaction = transactionService.getLowestUnqueBid(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Transaction> addItem(@RequestBody Transaction transaction){
        transaction.setCreatedAt(LocalDateTime.now());
        Transaction newTransaction = transactionService.addTransaction(transaction);
        return new ResponseEntity<Transaction>(newTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){
        Transaction updatedTransaction = transactionService.updateTransaction(transaction);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("id")Long id){
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
