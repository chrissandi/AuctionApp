package com.example.AuctionApp.service;

import com.example.AuctionApp.exception.ItemNotFoundException;
import com.example.AuctionApp.model.Item;
import com.example.AuctionApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Item updateItem(Item item){
        return itemRepository.save(item);
    }

    public Item findItemById(Long id){
        return itemRepository.findItemById(id)
                .orElseThrow(()->new ItemNotFoundException("Item by id "+id+" was not found"));
    }

    public void deleteItemById(Long id){
        itemRepository.deleteItemById(id);
    }
}
