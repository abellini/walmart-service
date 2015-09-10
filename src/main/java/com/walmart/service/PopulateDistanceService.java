package com.walmart.service;

import java.util.Collection;

import com.walmart.model.Route;

public interface PopulateDistanceService {
	
	public abstract void createDistance(final String map, final Collection<Route> routes) throws Exception;

}
