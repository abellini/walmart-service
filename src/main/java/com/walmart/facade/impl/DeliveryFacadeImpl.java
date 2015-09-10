package com.walmart.facade.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.facade.DeliveryFacade;
import com.walmart.model.Delivery;
import com.walmart.service.DeliveryService;

@Service("deliveryFacade")
public class DeliveryFacadeImpl implements DeliveryFacade {

	@Autowired
	DeliveryService deliveryService;

	@Override
	public BigDecimal calculate(Delivery delivery) {

		try {
			return deliveryService.calculateMinDistance(delivery);
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		}		
		return new BigDecimal(-1);
	}

}
