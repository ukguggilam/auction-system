package com.auction.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="ITEM")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemId;

	@NonNull
	private String description;

	@OneToOne(mappedBy = "item")
	@JsonIgnore
	private AuctionItemsEntity auctionItems;

}
