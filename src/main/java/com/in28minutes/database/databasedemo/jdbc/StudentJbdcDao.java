package com.in28minutes.database.databasedemo.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.in28minutes.database.databasedemo.entity.Student;

@Repository
public class StudentJbdcDao {
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	@Autowired
	DataSource dataSource;

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

	public void delete(Integer id) {
		String SQL = "delete from Student where id = ?";
		jdbcTemplateObject.update(SQL, id);
		System.out.println("Deleted Record with ID = " + id);
		return;
	}

	public Student getStudentByStoredProcedure(Integer id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("getRecord");

		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		Map<String, Object> out = jdbcCall.execute(in);

		Student student = new Student();
		student.setId(id);
		student.setName((String) out.get("out_name"));
		student.setAge((Integer) out.get("out_age"));
		return student;
	}

	public Student getStudentByStoredFunction(Integer id) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withFunctionName("get_student_name");
		SqlParameterSource in = new MapSqlParameterSource().addValue("in_id", id);
		String name = jdbcCall.executeFunction(String.class, in);

		Student student = new Student();
		student.setId(id);
		student.setName(name);
		return student;
	}

	public void batchUpdate(final List<Student> students) {
		String SQL = "update Student set age = ? where id = ?";
		int[] updateCounts = jdbcTemplateObject.batchUpdate(SQL, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, students.get(i).getAge());
				ps.setInt(2, students.get(i).getId());
			}

			@Override
			public int getBatchSize() {
				return students.size();
			}
		});
		System.out.println("Records updated!");
	}

	public void batchUpdateUsingObjects(final List<Student> students) {
		String SQL = "update Student set age = :age where id = :id";
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(students.toArray());
		NamedParameterJdbcTemplate jdbcTemplateObject = new NamedParameterJdbcTemplate(dataSource);

		int[] updateCounts = jdbcTemplateObject.batchUpdate(SQL, batch);
		System.out.println("Records updated!");
	}

	public void multipleBatchUpdate(final List<Student> students) {
		String SQL = "update Student set age = ? where id = ?";
		int[][] updateCounts = jdbcTemplateObject.batchUpdate(SQL, students, 1,
				new ParameterizedPreparedStatementSetter<Student>() {
					@Override
					public void setValues(PreparedStatement ps, Student student) throws SQLException {
						ps.setInt(1, student.getAge());
						ps.setInt(2, student.getId());
					}
				});
		System.out.println("Records updated!");
	}

	public void createWithSimpeJdbcInsert(String name, Integer age) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);
		parameters.put("age", age);

		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("Student");
		jdbcInsert.execute(parameters);
		System.out.println("Created Record Name = " + name + " Age = " + age);
		return;
	}

}