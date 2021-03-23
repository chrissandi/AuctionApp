package com.example.AuctionApp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name ="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name ="item_id")
    private Item item;

    private float priceBid;
    private LocalDateTime createdAt;

    public Transaction(User user, Item item, float priceBid, LocalDateTime createdAt) {
        this.user = user;
        this.item = item;
        this.priceBid = priceBid;
        this.createdAt = createdAt;
    }

    public Transaction(){
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public float getPriceBid() {
        return priceBid;
    }

    public void setPriceBid(float priceBid) {
        this.priceBid = priceBid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
