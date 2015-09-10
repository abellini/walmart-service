package com.walmart.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.dao.DistanceDao;
import com.walmart.model.Delivery;
import com.walmart.service.DeliveryService;

@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DistanceDao distanceDao;

	@Override
	public BigDecimal calculateMinDistance(Delivery delivery) throws Exception {
		
		System.out.println(">>>> Calculate <<<<");
		BigDecimal minDistance = distanceDao.calculateMinDistance(delivery.getMap(), delivery.getFrom(),
				delivery.getTo());
		
		final BigDecimal kml = minDistance.divide(delivery.getAutonomy(), 10, RoundingMode.FLOOR);
		final BigDecimal consumption = kml.multiply(delivery.getPrice());
		return consumption.setScale(2, RoundingMode.CEILING);
	}
	
}
