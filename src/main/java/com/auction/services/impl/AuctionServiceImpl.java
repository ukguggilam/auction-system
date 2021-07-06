package com.auction.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.auction.dtos.AuctionItemDto;
import com.auction.entity.AuctionItemsEntity;
import com.auction.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auction.repository.AuctionRepository;
import com.auction.services.AuctionService;

@Service
public class AuctionServiceImpl implements  AuctionService{

	@Autowired
	AuctionRepository auctionRepository;

	@Override
	public AuctionItemsEntity getById(long id) throws Exception {
		AuctionItemsEntity auctionItemsEntity = auctionRepository.findById(id).get();
		return auctionItemsEntity != null ? auctionItemsEntity : null;
	}

	@Override
	public AuctionItemsEntity addNewAuction(AuctionItemsEntity auctionItem) throws Exception {
		auctionItem.setCreationDate(new Date());
		return auctionRepository.save(auctionItem);
	}

	@Override
	public AuctionItemsEntity updateAuction(long id, AuctionItemsEntity auctionItem) throws Exception {
		if(auctionRepository.existsById(id))
			return auctionRepository.save(auctionItem);
		throw new RuntimeException("No such Auction exists with Auction id -> "+id);
	}

	@Override
	public AuctionItemsEntity deleteAuction(long id) throws Exception {
		if(auctionRepository.existsById(id)) {
			AuctionItemsEntity u = auctionRepository.findById(Long.valueOf(id)).get();
			auctionRepository.delete(u);
			return u;
		}
		throw new RuntimeException("No such Auction exists with Auction id -> "+id);
	}

	@Override
	public List<AuctionItemDto> getActiveAuctionItems() throws Exception {
		List<Object[]> items = auctionRepository.findAllActiveAuctionItems();
		List<AuctionItemDto> itemDtos = new ArrayList<>();
		for (Object[] object:items) {
			AuctionItemDto itemDto = new AuctionItemDto();
			itemDto.setAuctionItemId(((BigInteger)object[0]).longValue());
			itemDto.setCurrentBid((Double)object[1]);
			itemDto.setReservePrice((Double)object[2]);
			itemDto.setBidderName((String)object[3]);
			ItemEntity item = new ItemEntity();
			item.setItemId(((BigInteger)object[4]).longValue());
			item.setDescription((String)object[5]);
			itemDto.setItem(item);
			itemDtos.add(itemDto);
		}
		return itemDtos;
	}
}