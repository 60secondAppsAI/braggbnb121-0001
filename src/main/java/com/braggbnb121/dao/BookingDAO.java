package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.Booking;





public interface BookingDAO extends GenericDAO<Booking, Integer> {
  
	List<Booking> findAll();
	






}


