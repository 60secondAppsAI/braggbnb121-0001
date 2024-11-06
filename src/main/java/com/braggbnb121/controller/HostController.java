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

import com.braggbnb121.domain.Host;
import com.braggbnb121.dto.HostDTO;
import com.braggbnb121.dto.HostSearchDTO;
import com.braggbnb121.dto.HostPageDTO;
import com.braggbnb121.service.HostService;
import com.braggbnb121.dto.common.RequestDTO;
import com.braggbnb121.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/host")
@RestController
public class HostController {

	private final static Logger logger = LoggerFactory.getLogger(HostController.class);

	@Autowired
	HostService hostService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Host> getAll() {

		List<Host> hosts = hostService.findAll();
		
		return hosts;	
	}

	@GetMapping(value = "/{hostId}")
	@ResponseBody
	public HostDTO getHost(@PathVariable Integer hostId) {
		
		return (hostService.getHostDTOById(hostId));
	}

 	@RequestMapping(value = "/addHost", method = RequestMethod.POST)
	public ResponseEntity<?> addHost(@RequestBody HostDTO hostDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostService.addHost(hostDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/hosts")
	public ResponseEntity<HostPageDTO> getHosts(HostSearchDTO hostSearchDTO) {
 
		return hostService.getHosts(hostSearchDTO);
	}	

	@RequestMapping(value = "/updateHost", method = RequestMethod.POST)
	public ResponseEntity<?> updateHost(@RequestBody HostDTO hostDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = hostService.updateHost(hostDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
