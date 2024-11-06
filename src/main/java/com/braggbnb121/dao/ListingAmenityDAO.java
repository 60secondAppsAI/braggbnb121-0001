package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.ListingAmenity;





public interface ListingAmenityDAO extends GenericDAO<ListingAmenity, Integer> {
  
	List<ListingAmenity> findAll();
	






}


