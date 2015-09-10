package com.walmart.model;

import java.util.Set;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class Distance {

	@NotEmpty
	private String map;
	@Valid
	@NotEmpty
	private Set<Route> routes;
	
	public Distance() {
	}
	
	public Distance(String map, Set<Route> routes) {
		this.map = map;
		this.routes = routes;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	
}
