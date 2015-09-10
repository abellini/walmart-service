package com.walmart.service.impl;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.dao.DistanceDao;
import com.walmart.model.Route;
import com.walmart.service.PopulateDistanceService;

@Service("populateDistanceService")
public class PopulateDistanceServiceImpl implements PopulateDistanceService {

	@Autowired
	private DistanceDao distanceDao;

	public void createDistance(String map, Collection<Route> routes) throws Exception {

		for (Iterator<Route> routeIt = routes.iterator(); routeIt.hasNext();) {
			Route route = routeIt.next();

			if (!distanceDao.findNode(map, route.getFrom())) {
				distanceDao.createNode(map, route.getFrom());
			}
			
			if (!distanceDao.findNode(map, route.getTo())) {
				distanceDao.createNode(map, route.getTo());
			}

			if (distanceDao.findRelationship(map, route.getFrom(), route.getTo())) {
				distanceDao.updateRelationship(map, route.getFrom(), route.getTo(), route.getAmount());
			} else {
				distanceDao.createRelationship(map, route.getFrom(), route.getTo(), route.getAmount());
			}
		}

	}
}
