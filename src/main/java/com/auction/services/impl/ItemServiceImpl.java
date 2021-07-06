package com.auction.services.impl;

import com.auction.entity.ItemEntity;
import com.auction.repository.ItemRepository;
import com.auction.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public ItemEntity getById(String id) throws Exception {
        return itemRepository.findById(Long.valueOf(id)).get();
    }

    @Override
    public ItemEntity addNewItem(ItemEntity item) throws Exception {
        return itemRepository.save(item);
    }

    @Override
    public List<ItemEntity> addNewItems(List<ItemEntity> items) throws Exception {
    	Iterable<ItemEntity> dbEntites = itemRepository.saveAll(items);
    	
    	 return StreamSupport
                 .stream(dbEntites.spliterator(), false)
                 .collect(Collectors.toList());
    }

    @Override
    public ItemEntity updateItem(String id, ItemEntity item) throws Exception {
        if (itemRepository.existsById(Long.valueOf(id)))
            return itemRepository.save(item);
        throw new RuntimeException("No such Product exists with Product id -> " + id);
    }

    @Override
    public ItemEntity deleteItem(String id) throws Exception {
        if (itemRepository.existsById(Long.valueOf(id))) {
            ItemEntity u = itemRepository.findById(Long.valueOf(id)).get();
            itemRepository.delete(u);
            return u;
        }
        throw new RuntimeException("No such Product exists with Product id -> " + id);
    }

    @Override
    public List<ItemEntity> getItems() throws Exception {
        return StreamSupport
                .stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}