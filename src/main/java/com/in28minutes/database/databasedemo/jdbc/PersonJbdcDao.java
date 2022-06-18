package com.in28minutes.database.databasedemo.jdbc;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

@Repository
public class PersonJbdcDao {

	@Autowired
	DataSource dataSource;

	// Handling CLOB example
	public void updateDescription(Integer id, String description) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", id);
		in.addValue("description", new SqlLobValue(description, new DefaultLobHandler()), Types.CLOB);

		String SQL = "update Person set description = :description where id = :id";
		NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);

		jdbcTemplateObject.update(SQL, in);
		System.out.println("Updated Record with ID = " + id);
	}

}