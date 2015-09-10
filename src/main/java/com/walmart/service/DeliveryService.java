package com.walmart.service;

import java.math.BigDecimal;

import com.walmart.model.Delivery;

public interface DeliveryService {

	public abstract BigDecimal calculateMinDistance(Delivery delivery) throws Exception;
}
