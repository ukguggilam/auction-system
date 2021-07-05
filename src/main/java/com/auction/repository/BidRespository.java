package com.auction.repository;

import com.auction.entity.BidsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BidRespository extends CrudRepository<BidsEntity, Long> {

    @Query(value = "SELECT * FROM BID_INFO WHERE AUCTION_ITEM_ID = :auctionItemId",
            nativeQuery = true)
    List<BidsEntity> findAllByAuctionId(@Param("auctionItemId")long auctionItemId);
}
