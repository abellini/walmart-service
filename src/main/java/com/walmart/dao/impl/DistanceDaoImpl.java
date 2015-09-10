package com.walmart.dao.impl;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.walmart.dao.DistanceDao;

@Service("distanceDao")
public class DistanceDaoImpl implements DistanceDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void createNode(String map, String name) {
		this.jdbcTemplate.execute(String.format("CREATE (City%s%s:City {maps:'%s', name:'%s'})", trimValue(name),
				trimValue(map), map, name));
	}

	@Override
	public boolean findNode(String map, String name) {
		String query = String.format("MATCH (y:City {maps:'%s', name:'%s'})", map, name);
		query = query.concat(" RETURN count(y)");
		
		final Integer queryResult = this.jdbcTemplate.queryForObject(query, Integer.class);
		System.out.println("---> " + map + " " + name + "::" + String.valueOf(queryResult > 1));
		return queryResult > 0;
	}

	@Override
	public void createRelationship(String map, String from, String to, BigDecimal amount) {
		String query = String.format("MATCH (from:City {maps:'%s', name:'%s'}),(to:City {maps:'%s', name:'%s'})", map,
				from, map, to);
		query = query.concat(" CREATE");
		query = query.concat(String.format("(from)-[:DISTANCE { distance: %s }]->(to)", amount.toString()));
		query = query.concat(" RETURN from, to");
		this.jdbcTemplate.execute(query);
	}

	@Override
	public void updateRelationship(String map, String from, String to, BigDecimal amount) {
		String query = String.format(
				"MATCH (from:City {name:'%s', maps:'%s'})-[r:DISTANCE]->(to:City {name:'%s', maps:'%s'})", from, map,
				to, map);
		query = query.concat(String.format(" SET r.distance = %s", amount.toString()));
		query = query.concat(" RETURN count(r)");

		this.jdbcTemplate.execute(query);
	}

	@Override
	public boolean findRelationship(String map, String from, String to) {
		String query = String.format(
				"MATCH (from:City {maps:'%s', name:'%s'})-[y:DISTANCE]->(to:City {maps:'%s', name:'%s'})", map, from,
				map, to);
		query = query.concat("RETURN count(y)");

		final Integer queryResult = this.jdbcTemplate.queryForObject(query, Integer.class);
		return queryResult > 0;
	}

	@Override
	public BigDecimal calculateMinDistance(String map, String from, String to) {
		String query = String.format("MATCH (from:City {maps:'%s', name:'%s'}),(to:City {maps:'%s', name:'%s'}),", map,
				from, map, to);
		query = query.concat(" path = (from)-[rels:DISTANCE*]->(to)");
		query = query.concat(" RETURN");
		query = query.concat(" reduce(distance=0, y in rels | distance+y.distance) AS totalDistance");
		query = query.concat(" ORDER BY totalDistance ASC");
		query = query.concat(" LIMIT 1");
		
		return this.jdbcTemplate.queryForObject(query, BigDecimal.class);
		
	}

	private static String trimValue(Object value) {
		return value != null ? value.toString().replace(" ", "") : "";
	}

}
