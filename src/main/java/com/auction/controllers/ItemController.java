package com.auction.controllers;

import com.auction.entity.ItemEntity;
import com.auction.model.Response;
import com.auction.model.ResponseTypeEnum;
import com.auction.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/items")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@RequestMapping(value="/{ItemId}", method = RequestMethod.GET)
	public Response<?> getItemById(@PathVariable(value = "ItemId", required = true)String itemId) {
		try {
			return new Response<ItemEntity>(ResponseTypeEnum.SUCCESS,itemService.getById(itemId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public Response<?> getItems() {
		try {
			return new Response<List<ItemEntity>>(ResponseTypeEnum.SUCCESS,itemService.getItems());
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public Response<?> addNewItem(@RequestBody ItemEntity item) {
		try {
			return new Response<ItemEntity>(ResponseTypeEnum.SUCCESS,itemService.addNewItem(item));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage()); 
		}
	}

	@RequestMapping(value="/list", method = RequestMethod.POST)
	public Response<?> addNewItems(@RequestBody List<ItemEntity> items) {
		try {

			return new Response<List<ItemEntity>>(ResponseTypeEnum.SUCCESS,itemService.addNewItems(items));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{ItemId}", method = RequestMethod.PUT)
	public Response<?> updateItem(@PathVariable(value = "ItemId", required = true)String itemId,@RequestBody ItemEntity item) {
		try {
			return new Response<ItemEntity>(ResponseTypeEnum.SUCCESS,itemService.updateItem(itemId, item));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{ItemId}", method = RequestMethod.DELETE)
	public Response<?> deleteItemById(@PathVariable(value = "ItemId", required = true)String itemId) {
		try {
			return new Response<ItemEntity>(ResponseTypeEnum.SUCCESS,itemService.deleteItem(itemId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

}
