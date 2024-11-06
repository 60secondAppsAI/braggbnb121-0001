package com.braggbnb121.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListingAmenityPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<ListingAmenityDTO> listingAmenitys;
}





