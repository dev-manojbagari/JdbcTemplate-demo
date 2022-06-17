package com.in28minutes.database.databasedemo.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Student;

@Repository
public class StudentJbdcDao {
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	public void create(String name, Integer age) {
		String SQL = "insert into Student (name, age) values (?, ?)";

		jdbcTemplateObject.update(SQL, name, age);
		System.out.println("Created Record Name = " + name + " Age = " + age);
		return;
	}

	public List<Student> findAll() {
		return jdbcTemplateObject.query("select * from student", new BeanPropertyRowMapper<Student>(Student.class));
	}

	public void update(Integer id, Integer age) {
		String SQL = "update Student set age = ? where id = ?";
		jdbcTemplateObject.update(SQL, age, id);
		System.out.println("Updated Record with ID = " + id);
		return;
	}

	public Student findById(Integer id) {
		String SQL = "select * from Student where id = ?";
		Student student = jdbcTemplateObject.queryForObject(SQL, new BeanPropertyRowMapper<Student>(Student.class),
				new Object[] { id });
		return student;
	}

}