package com.auction.services;

import com.auction.entity.BidsEntity;
import com.auction.model.Response;

import java.util.List;

public interface BidService {

    Response<?> addNewBid(BidsEntity bid)throws Exception;

    List<BidsEntity> getBids();

    List<BidsEntity> getBidsByAuctionId(long auctionItemId);
}
