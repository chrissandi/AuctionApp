package com.example.AuctionApp.repository;


import com.example.AuctionApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    void deleteTransactionById(Long id);

    @Query(value = "SELECT * FROM transaction WHERE price_bid = (SELECT MAX(price_bid) as 'max_price' FROM transaction) " +
            "and item_id = ?1 ",
            nativeQuery = true)
    Transaction getHighestBid(Long item_id);

    @Query(value = "SELECT *\n" +
            "FROM (SELECT* \n" +
            "      FROM (SELECT COUNT(price_bid) as cnt, id\n" +
            "\t\t\tFROM transaction\n" +
            "\t\t\tGROUP BY price_bid) as temp\n" +
            "      \t\tWHERE temp.cnt = 1) AS tab \n" +
            "      LEFT JOIN transaction on tab.id = transaction.id\n" +
            "WHERE item_id = ?1\n" +
            "ORDER BY price_bid ASC\n" +
            "LIMIT 1",
            nativeQuery = true)
    Transaction getLowestUniqueBid(Long item_id);
    Optional<Transaction> findTransactionById(Long id);

}
