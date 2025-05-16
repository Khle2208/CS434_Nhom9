package com.nhom9.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.OrderDetailDAO;
import fa.mockproject.model.Order;
import fa.mockproject.model.OrderDetail;

@Service
public class OrderDetailService implements ServiceInterface<OrderDetail>{

	@Autowired
	OrderDetailDAO orderDetailDao;

	@Override
	public int insert(OrderDetail t) {
		return orderDetailDao.insert(t);
	}

	@Override
	public int update(OrderDetail t) {
		return orderDetailDao.update(t);
	}

	@Override
	public int delete(OrderDetail t) {
		return orderDetailDao.delete(t);
	}

	@Override
	public ArrayList<OrderDetail> findAll() {
		return orderDetailDao.findAll();
	}

	@Override
	public OrderDetail findById(OrderDetail t) {
		return orderDetailDao.findById(t);
	}
	
	public ArrayList<OrderDetail> getItemsByOrderID(UUID orderId){
		return orderDetailDao.getItemsByOrderID(orderId);
	}
	
	//KB
	public ArrayList<OrderDetail> findByOrderId(Order o) {
		return orderDetailDao.findByOrderId(o);
	}
}
