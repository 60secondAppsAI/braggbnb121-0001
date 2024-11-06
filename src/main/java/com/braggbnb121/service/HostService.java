package com.braggbnb121.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb121.domain.Host;
import com.braggbnb121.dto.HostDTO;
import com.braggbnb121.dto.HostSearchDTO;
import com.braggbnb121.dto.HostPageDTO;
import com.braggbnb121.dto.HostConvertCriteriaDTO;
import com.braggbnb121.service.GenericService;
import com.braggbnb121.dto.common.RequestDTO;
import com.braggbnb121.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface HostService extends GenericService<Host, Integer> {

	List<Host> findAll();

	ResultDTO addHost(HostDTO hostDTO, RequestDTO requestDTO);

	ResultDTO updateHost(HostDTO hostDTO, RequestDTO requestDTO);

    Page<Host> getAllHosts(Pageable pageable);

    Page<Host> getAllHosts(Specification<Host> spec, Pageable pageable);

	ResponseEntity<HostPageDTO> getHosts(HostSearchDTO hostSearchDTO);
	
	List<HostDTO> convertHostsToHostDTOs(List<Host> hosts, HostConvertCriteriaDTO convertCriteria);

	HostDTO getHostDTOById(Integer hostId);







}





