package com.auction.services;

import java.util.List;

import com.auction.dtos.AuctionItemDto;
import com.auction.entity.AuctionItemsEntity;

public interface AuctionService {

	Long getById(long id)throws Exception;

	List<AuctionItemDto> getActiveAuctionItems() throws Exception;

	AuctionItemsEntity addNewAuction(AuctionItemsEntity auction)throws Exception;

	AuctionItemsEntity updateAuction(long id, AuctionItemsEntity auction)throws Exception;

	AuctionItemsEntity deleteAuction(long id)throws Exception;
}
