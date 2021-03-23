package com.example.AuctionApp.service;

import com.example.AuctionApp.exception.TransactionNotFoundException;
import com.example.AuctionApp.model.Transaction;
import com.example.AuctionApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction getHighestBid(Long id){
        return transactionRepository.getHighestBid(id);
    }
    public Transaction getLowestUnqueBid(Long id){
        return transactionRepository.getLowestUniqueBid(id);
    }

    public List<Transaction> findAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction findTransactionById(Long id){
        return transactionRepository.findTransactionById(id)
                .orElseThrow(()->new TransactionNotFoundException("Transaction by id "+id+" was not found"));
    }

    public void deleteTransactionById(Long id){
        transactionRepository.deleteTransactionById(id);
    }
}
