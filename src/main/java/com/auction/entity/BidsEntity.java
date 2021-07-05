package com.auction.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="BID_INFO")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BidsEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bidId;

	@NonNull
	private String bidderName;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "auctionItemId")
	private AuctionItemsEntity auctionItems;

	@NonNull
	private Double maxAutoBidAmount;

	@NonNull
	private Date creationDate;

	private Date updateDate;

	private boolean reserveStatus;

	private String bidStatus;
}
