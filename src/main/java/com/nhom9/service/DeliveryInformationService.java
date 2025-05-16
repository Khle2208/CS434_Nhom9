package com.nhom9.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.mockproject.dao.DeliveryInformationDAO;
import fa.mockproject.model.DeliveryInformation;

@Service
public class DeliveryInformationService implements ServiceInterface<DeliveryInformation>{
	@Autowired
	DeliveryInformationDAO deliveryInformationDao;
	@Override
	public int insert(DeliveryInformation t) {
		return deliveryInformationDao.insert(t);
	}

	@Override
	public int update(DeliveryInformation t) {
		return deliveryInformationDao.update(t);
	}

	@Override
	public int delete(DeliveryInformation t) {
		return deliveryInformationDao.delete(t);
	}

	@Override
	public ArrayList<DeliveryInformation> findAll() {
		return deliveryInformationDao.findAll();
	}

	@Override
	public DeliveryInformation findById(DeliveryInformation t) {
		return deliveryInformationDao.findById(t);
	}

}
