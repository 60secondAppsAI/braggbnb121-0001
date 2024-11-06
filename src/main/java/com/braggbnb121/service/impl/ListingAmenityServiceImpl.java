package com.braggbnb121.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.service.GenericService;
import com.braggbnb121.service.impl.GenericServiceImpl;
import com.braggbnb121.dao.ListingAmenityDAO;
import com.braggbnb121.domain.ListingAmenity;
import com.braggbnb121.dto.ListingAmenityDTO;
import com.braggbnb121.dto.ListingAmenitySearchDTO;
import com.braggbnb121.dto.ListingAmenityPageDTO;
import com.braggbnb121.dto.ListingAmenityConvertCriteriaDTO;
import com.braggbnb121.dto.common.RequestDTO;
import com.braggbnb121.dto.common.ResultDTO;
import com.braggbnb121.service.ListingAmenityService;
import com.braggbnb121.util.ControllerUtils;





@Service
public class ListingAmenityServiceImpl extends GenericServiceImpl<ListingAmenity, Integer> implements ListingAmenityService {

    private final static Logger logger = LoggerFactory.getLogger(ListingAmenityServiceImpl.class);

	@Autowired
	ListingAmenityDAO listingAmenityDao;

	


	@Override
	public GenericDAO<ListingAmenity, Integer> getDAO() {
		return (GenericDAO<ListingAmenity, Integer>) listingAmenityDao;
	}
	
	public List<ListingAmenity> findAll () {
		List<ListingAmenity> listingAmenitys = listingAmenityDao.findAll();
		
		return listingAmenitys;	
		
	}

	public ResultDTO addListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO) {

		ListingAmenity listingAmenity = new ListingAmenity();

		listingAmenity.setListingAmenityId(listingAmenityDTO.getListingAmenityId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		listingAmenity = listingAmenityDao.save(listingAmenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ListingAmenity> getAllListingAmenitys(Pageable pageable) {
		return listingAmenityDao.findAll(pageable);
	}

	public Page<ListingAmenity> getAllListingAmenitys(Specification<ListingAmenity> spec, Pageable pageable) {
		return listingAmenityDao.findAll(spec, pageable);
	}

	public ResponseEntity<ListingAmenityPageDTO> getListingAmenitys(ListingAmenitySearchDTO listingAmenitySearchDTO) {
	
			Integer listingAmenityId = listingAmenitySearchDTO.getListingAmenityId(); 
 			String sortBy = listingAmenitySearchDTO.getSortBy();
			String sortOrder = listingAmenitySearchDTO.getSortOrder();
			String searchQuery = listingAmenitySearchDTO.getSearchQuery();
			Integer page = listingAmenitySearchDTO.getPage();
			Integer size = listingAmenitySearchDTO.getSize();

	        Specification<ListingAmenity> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, listingAmenityId, "listingAmenityId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<ListingAmenity> listingAmenitys = this.getAllListingAmenitys(spec, pageable);
		
		//System.out.println(String.valueOf(listingAmenitys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(listingAmenitys.getTotalPages()));
		
		List<ListingAmenity> listingAmenitysList = listingAmenitys.getContent();
		
		ListingAmenityConvertCriteriaDTO convertCriteria = new ListingAmenityConvertCriteriaDTO();
		List<ListingAmenityDTO> listingAmenityDTOs = this.convertListingAmenitysToListingAmenityDTOs(listingAmenitysList,convertCriteria);
		
		ListingAmenityPageDTO listingAmenityPageDTO = new ListingAmenityPageDTO();
		listingAmenityPageDTO.setListingAmenitys(listingAmenityDTOs);
		listingAmenityPageDTO.setTotalElements(listingAmenitys.getTotalElements());
		return ResponseEntity.ok(listingAmenityPageDTO);
	}

	public List<ListingAmenityDTO> convertListingAmenitysToListingAmenityDTOs(List<ListingAmenity> listingAmenitys, ListingAmenityConvertCriteriaDTO convertCriteria) {
		
		List<ListingAmenityDTO> listingAmenityDTOs = new ArrayList<ListingAmenityDTO>();
		
		for (ListingAmenity listingAmenity : listingAmenitys) {
			listingAmenityDTOs.add(convertListingAmenityToListingAmenityDTO(listingAmenity,convertCriteria));
		}
		
		return listingAmenityDTOs;

	}
	
	public ListingAmenityDTO convertListingAmenityToListingAmenityDTO(ListingAmenity listingAmenity, ListingAmenityConvertCriteriaDTO convertCriteria) {
		
		ListingAmenityDTO listingAmenityDTO = new ListingAmenityDTO();
		
		listingAmenityDTO.setListingAmenityId(listingAmenity.getListingAmenityId());

	

		
		return listingAmenityDTO;
	}

	public ResultDTO updateListingAmenity(ListingAmenityDTO listingAmenityDTO, RequestDTO requestDTO) {
		
		ListingAmenity listingAmenity = listingAmenityDao.getById(listingAmenityDTO.getListingAmenityId());

		listingAmenity.setListingAmenityId(ControllerUtils.setValue(listingAmenity.getListingAmenityId(), listingAmenityDTO.getListingAmenityId()));



        listingAmenity = listingAmenityDao.save(listingAmenity);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ListingAmenityDTO getListingAmenityDTOById(Integer listingAmenityId) {
	
		ListingAmenity listingAmenity = listingAmenityDao.getById(listingAmenityId);
			
		
		ListingAmenityConvertCriteriaDTO convertCriteria = new ListingAmenityConvertCriteriaDTO();
		return(this.convertListingAmenityToListingAmenityDTO(listingAmenity,convertCriteria));
	}







}
