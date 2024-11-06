package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.Review;





public interface ReviewDAO extends GenericDAO<Review, Integer> {
  
	List<Review> findAll();
	






}


