package com.auction.dtos;

import com.auction.entity.ItemEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuctionItemDto implements Serializable {

    private Long auctionItemId;

    private Double currentBid;

    private Double reservePrice;

    private String bidderName;

    private ItemEntity item;

    public AuctionItemDto(Long auctionItemId) {
        this.auctionItemId = auctionItemId;
    }
}
