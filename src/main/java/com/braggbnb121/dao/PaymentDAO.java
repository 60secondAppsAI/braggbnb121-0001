package com.braggbnb121.dao;

import java.util.List;

import com.braggbnb121.dao.GenericDAO;
import com.braggbnb121.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


