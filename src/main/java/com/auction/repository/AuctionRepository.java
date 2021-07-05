package com.auction.repository;

import com.auction.entity.AuctionItemsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends CrudRepository<AuctionItemsEntity,Long> {

    @Query(value = "SELECT AI.AUCTION_ITEM_ID , AI.CURRENT_BID , AI.RESERVE_PRICE , B.BIDDER_NAME , I.ITEM_ID , I.DESCRIPTION  FROM AUCTION_ITEMS AI, ITEM I, BID_INFO B WHERE AI.AUCTION_ITEM_ID = B.AUCTION_ITEM_ID AND AI.ITEM_ID = I.ITEM_ID AND END_TIME > CURRENT_DATE()",
            nativeQuery = true)
    public List<Object[]> findAllActiveAuctionItems();
}
