package com.auction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auction.entity.BidsEntity;
import com.auction.model.Response;
import com.auction.model.ResponseTypeEnum;
import com.auction.services.BidService;

@RestController
@RequestMapping("v1/bids")
public class BidController {

    @Autowired
    BidService bidService;

    @RequestMapping(value="/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<?> addNewBid(@RequestBody BidsEntity bids) {
        try {
            return  bidService.addNewBid(bids);
        }
        catch(Exception e) {
            return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
        }
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public Response<?> getBids() {
        try {
            return new Response<List<BidsEntity>>(ResponseTypeEnum.SUCCESS, bidService.getBids());
        }
        catch(Exception e) {
            return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
        }
    }

    @RequestMapping(value="/{AuctionItemId}", method = RequestMethod.GET)
    public Response<?> getBidsByAcutionId(@PathVariable("AuctionItemId")long auctionItemId) {
        try {
            return new Response<List<BidsEntity>>(ResponseTypeEnum.SUCCESS, bidService.getBidsByAuctionId(auctionItemId));
        }
        catch(Exception e) {
            return new Response<String>(ResponseTypeEnum.ERROR,e.getMessage());
        }
    }
}
