package com.walmart.facade;

import java.math.BigDecimal;

import com.walmart.model.Delivery;

public interface DeliveryFacade {
	
	public abstract BigDecimal calculate(final Delivery delivery);

}
