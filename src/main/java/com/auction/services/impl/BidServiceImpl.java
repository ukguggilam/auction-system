package com.auction.services.impl;

import com.auction.entity.AuctionItemsEntity;
import com.auction.entity.BidsEntity;
import com.auction.model.Response;
import com.auction.model.ResponseTypeEnum;
import com.auction.repository.AuctionRepository;
import com.auction.repository.BidRespository;
import com.auction.services.BidService;
import com.auction.utils.AuctionStatusMessages;
import com.auction.utils.BidStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidRespository bidRespository;

    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public Response<?> addNewBid(BidsEntity bid) throws Exception {
        Response<?> response = null;
        AuctionItemsEntity auctionItem = auctionRepository.findById(bid.getAuctionItems().getAuctionItemId()).get();
        if(auctionItem != null) {
        	// not meeting the reserve price
            if (bid.getMaxAutoBidAmount() <= auctionItem.getReservePrice()) {
                auctionItem.setCurrentBid(bid.getMaxAutoBidAmount());
                bid.setReserveStatus(false);
                response = new Response<String>(ResponseTypeEnum.ERROR, AuctionStatusMessages.BID_RESERVE_PRICE_NOT_MET);
            } else {
                Double maxBidding = getMaxBidding(auctionItem.getBids());
                //current bid less than existing bidding value
                if (maxBidding >= bid.getMaxAutoBidAmount()) {
                    bid.setBidStatus(BidStatusEnum.OUTBID.name());
                    response = new Response<String>(ResponseTypeEnum.ERROR, AuctionStatusMessages.MAX_AMOUNT_OUT_BID);
                }
                //current bid is max value and make out bid status to existing
                if (bid.getMaxAutoBidAmount() > maxBidding) {
                    bid.setReserveStatus(true);
                    bid.setBidStatus(BidStatusEnum.MAXBID.name());
                    auctionItem.setCurrentBid(bid.getMaxAutoBidAmount());
                    //update all existing max bids to out bid
                    updateMaxBidsToOutBid(auctionItem.getBids());
                }
            }
            bid.setAuctionItems(auctionItem);
            bidRespository.save(bid);
            if (response == null) {
                response = new Response<String>(ResponseTypeEnum.SUCCESS, AuctionStatusMessages.BID_ADD_SUCCESS);
            }
        } else {
            response = new Response<String>(ResponseTypeEnum.ERROR, AuctionStatusMessages.AUCTION_ITEM_NOT_FOUND);
        }
        return response;
    }

    private void updateMaxBidsToOutBid(List<BidsEntity> bidding) {
        for(BidsEntity bid : bidding) {
            if(StringUtils.equalsIgnoreCase(bid.getBidStatus(), BidStatusEnum.MAXBID.name())) {
                bid.setBidStatus(BidStatusEnum.OUTBID.name());
            }
        }
    }

    private Double getMaxBidding(List<BidsEntity> bidding) {
		Double max = Double.MIN_VALUE;
		for(BidsEntity bid : bidding)
			max = Double.max(bid.getMaxAutoBidAmount(),max);
		return max;
	}

    @Override
    public List<BidsEntity> getBids() {
        return StreamSupport
                .stream(bidRespository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<BidsEntity> getBidsByAuctionId(long auctionItemId) {
        return bidRespository.findAllByAuctionId(auctionItemId);
    }
}
