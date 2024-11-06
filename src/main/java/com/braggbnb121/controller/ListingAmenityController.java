package com.braggbnb121.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.braggbnb121.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.braggbnb121.domain.ListingAmenity;
import com.braggbnb121.dto.ListingAmenityDTO;
import com.braggbnb121.dto.ListingAmenitySearchDTO;
import com.braggbnb121.dto.ListingAmenityPageDTO;
import com.braggbnb121.service.ListingAmenityService;
import com.braggbnb121.dto.common.RequestDTO;
import com.braggbnb121.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/listingAmenity")
@RestController
public class ListingAmenityController {

	private final static Logger logger = LoggerFactory.getLogger(ListingAmenityController.class);

	@Autowired
	ListingAmenityService listingAmenityService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ListingAmenity> getAll() {

		List<ListingAmenity> listingAmenitys = listingAmenityService.findAll();
		
		return listingAmenitys;	
	}

	@GetMapping(value = "/{listingAmenityId}")
	@ResponseBody
	public ListingAmenityDTO getListingAmenity(@PathVariable Integer listingAmenityId) {
		
		return (listingAmenityService.getListingAmenityDTOById(listingAmenityId));
	}

 	@RequestMapping(value = "/addListingAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> addListingAmenity(@RequestBody ListingAmenityDTO listingAmenityDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = listingAmenityService.addListingAmenity(listingAmenityDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/listingAmenitys")
	public ResponseEntity<ListingAmenityPageDTO> getListingAmenitys(ListingAmenitySearchDTO listingAmenitySearchDTO) {
 
		return listingAmenityService.getListingAmenitys(listingAmenitySearchDTO);
	}	

	@RequestMapping(value = "/updateListingAmenity", method = RequestMethod.POST)
	public ResponseEntity<?> updateListingAmenity(@RequestBody ListingAmenityDTO listingAmenityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = listingAmenityService.updateListingAmenity(listingAmenityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
