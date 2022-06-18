package com.in28minutes.database.databasedemo.jdbc;

import java.io.ByteArrayInputStream;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeJbdcDao {

	@Autowired
	DataSource dataSource;

	// example for handling blob
	public void updateImage(Integer id, byte[] imageData) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", id);
		in.addValue("image",
				new SqlLobValue(new ByteArrayInputStream(imageData), imageData.length, new DefaultLobHandler()),
				Types.BLOB);

		String SQL = "update Employee set image = :image where id = :id";
		NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);

		jdbcTemplateObject.update(SQL, in);
		System.out.println("Updated Record with ID = " + id);
	}

}