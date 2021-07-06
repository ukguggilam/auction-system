package com.auction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auction.dtos.AuctionItemDto;
import com.auction.entity.AuctionItemsEntity;
import com.auction.model.Response;
import com.auction.model.ResponseTypeEnum;
import com.auction.services.AuctionService;

@RestController 
@RequestMapping("v1/auctionItems")
public class AuctionController {
	
	@Autowired
	AuctionService auctionService;
	
	@RequestMapping(value="/{AuctionItemId}", method = RequestMethod.GET)
	public Response<?> getAuctionById(@PathVariable("AuctionItemId")long auctionItemId) {
		try {
			return new Response<AuctionItemsEntity>(ResponseTypeEnum.SUCCESS, auctionService.getById(auctionItemId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> addNewAuction(@RequestBody AuctionItemsEntity auctionItems) {
		try {
			return new Response<AuctionItemDto>(ResponseTypeEnum.SUCCESS,new AuctionItemDto(auctionService.addNewAuction(auctionItems).getAuctionItemId()));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public Response<?> getAllAuctions() {
		try {
			return new Response<List<AuctionItemDto>>(ResponseTypeEnum.SUCCESS, auctionService.getActiveAuctionItems());
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{AuctionItemId}", method = RequestMethod.PUT)
	public Response<?> updateAuction(@PathVariable("AuctionItemId")long auctionItemId,@RequestBody AuctionItemsEntity auctionItems) {
		try {
			return new Response<AuctionItemsEntity>(ResponseTypeEnum.SUCCESS, auctionService.updateAuction(auctionItemId, auctionItems));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

	@RequestMapping(value="/{AuctionItemId}", method = RequestMethod.DELETE)
	public Response<?> deleteAuctionById(@PathVariable("AuctionItemId")long auctionItemId) {
		try {
			return new Response<AuctionItemsEntity>(ResponseTypeEnum.SUCCESS, auctionService.deleteAuction(auctionItemId));
		}
		catch(Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
		}
	}

}
