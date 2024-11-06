package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.Host;





public interface HostDAO extends GenericDAO<Host, Integer> {
  
	List<Host> findAll();
	






}


