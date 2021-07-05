package com.auction.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="AUCTION_ITEMS")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItemsEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long auctionItemId;

	private Double currentBid;

	@NonNull
	private Double reservePrice;

	@NonNull
	@OneToOne
	@JoinColumn(name = "itemId")
	private ItemEntity item;

	@OneToMany(mappedBy = "auctionItems")
	@JsonIgnore
	private List<BidsEntity> bids = new ArrayList<BidsEntity>();
	
	private Date startTime;
	
	private Date endTime;

	private Date creationDate;

	private Date updateDate;
}
