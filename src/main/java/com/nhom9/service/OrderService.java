package com.nhom9.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.OrderDAO;
import fa.mockproject.model.Order;

@Service
public class OrderService implements ServiceInterface<Order>{
	@Autowired
	OrderDAO orderDao;

	@Override
	public int insert(Order t) {
		return orderDao.insert(t);
	}

	@Override
	public int update(Order t) {
		return orderDao.update(t);
	}

	@Override
	public int delete(Order t) {
		return orderDao.delete(t);
	}

	@Override
	public ArrayList<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public Order findById(Order t) {
		return orderDao.findById(t);
	}
	//cuong creator
	
	public UUID addOrderbyId(String id_user, BigDecimal price, List<UUID> bookIds, UUID cartID) {
		return orderDao.addOrderbyId(id_user, price, bookIds, cartID);
	}
	
	public Order getOrderByID(UUID orderID) {
		return orderDao.getOrderByID(orderID);
	}
	
	public int updateStatus(UUID orderID, String status) {
		return orderDao.updateStatus(orderID, status);
	}
	
	public ArrayList<Order> getAllByUserId(UUID userId){
		return orderDao.getAllByUserId(userId);
	}
	
	public ArrayList<Order> getAllByUserIdAndStatus(UUID userId, String status){
		return orderDao.getAllByUserIdAndStatus(userId, status);
	}
	
	//Khoa creator
	public ArrayList<Order> filterByDate(String startDate, String endDate){
		return orderDao.filterByDate(startDate, endDate);
	}
	public ArrayList<Order> findByEmail(String email){
		return orderDao.findByEmail(email);
	}
	
	public ArrayList<Order> filterByStatus(String status){
		return orderDao.filterByStatus(status);
	}
	
	public ArrayList<Order> filterAll(String startDate, String endDate, String status){
		return orderDao.filterAll(startDate, endDate, status);
	}
	
	public int countOrderByUserId(UUID userId) {
		return orderDao.countOrderByUserId(userId);
	}
	
	public List<Object[]> chartRevenueService(int year) {
		return orderDao.chartRevenueController(year);
	}
	public List<Object[]>chartOrderService(int year) {
		return orderDao.chartOrderDAO(year);
	}
	public Integer statisticOrderService(Date startDate, Date endDate) {
		return orderDao.statisticOrderDAO(startDate, endDate);
	}
}
