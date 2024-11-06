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

import com.braggbnb121.domain.Guest;
import com.braggbnb121.dto.GuestDTO;
import com.braggbnb121.dto.GuestSearchDTO;
import com.braggbnb121.dto.GuestPageDTO;
import com.braggbnb121.service.GuestService;
import com.braggbnb121.dto.common.RequestDTO;
import com.braggbnb121.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/guest")
@RestController
public class GuestController {

	private final static Logger logger = LoggerFactory.getLogger(GuestController.class);

	@Autowired
	GuestService guestService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Guest> getAll() {

		List<Guest> guests = guestService.findAll();
		
		return guests;	
	}

	@GetMapping(value = "/{guestId}")
	@ResponseBody
	public GuestDTO getGuest(@PathVariable Integer guestId) {
		
		return (guestService.getGuestDTOById(guestId));
	}

 	@RequestMapping(value = "/addGuest", method = RequestMethod.POST)
	public ResponseEntity<?> addGuest(@RequestBody GuestDTO guestDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = guestService.addGuest(guestDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/guests")
	public ResponseEntity<GuestPageDTO> getGuests(GuestSearchDTO guestSearchDTO) {
 
		return guestService.getGuests(guestSearchDTO);
	}	

	@RequestMapping(value = "/updateGuest", method = RequestMethod.POST)
	public ResponseEntity<?> updateGuest(@RequestBody GuestDTO guestDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = guestService.updateGuest(guestDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
