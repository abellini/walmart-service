package com.walmart.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.facade.PopulateDistanceFacade;
import com.walmart.model.Distance;
import com.walmart.service.PopulateDistanceService;

@Service("populateDistanceFacade")
public class PopulateDistanceFacadeImpl implements PopulateDistanceFacade {

	@Autowired
	private PopulateDistanceService populate;

	public boolean create(final Distance distances) {

		try {
			populate.createDistance(distances.getMap(), distances.getRoutes());
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}