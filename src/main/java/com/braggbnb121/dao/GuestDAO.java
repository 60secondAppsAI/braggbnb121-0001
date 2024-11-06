package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.Guest;





public interface GuestDAO extends GenericDAO<Guest, Integer> {
  
	List<Guest> findAll();
	






}


