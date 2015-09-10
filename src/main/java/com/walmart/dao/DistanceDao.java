package com.walmart.dao;

import java.math.BigDecimal;

public interface DistanceDao {

	public abstract void createNode(String map, String name);

	public abstract boolean findNode(String map, String name);

	public abstract void createRelationship(String map, String from, String to, BigDecimal amount);

	public abstract void updateRelationship(String map, String from, String to, BigDecimal amount);

	public abstract boolean findRelationship(String map, String from, String to);

	public abstract BigDecimal calculateMinDistance(String map, String from, String to);
}
