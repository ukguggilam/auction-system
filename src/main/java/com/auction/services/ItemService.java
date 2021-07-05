package com.auction.services;

import com.auction.entity.ItemEntity;

import java.util.List;

public interface ItemService {
	
	ItemEntity getById(String id)throws Exception;
	
	ItemEntity addNewItem(ItemEntity item)throws Exception;

	List<ItemEntity> addNewItems(List<ItemEntity> items)throws Exception;
	
	ItemEntity updateItem(String id, ItemEntity item)throws Exception;

	ItemEntity deleteItem(String id)throws Exception;

	List<ItemEntity> getItems()throws Exception;

}
