package com.database.jdbctemplate;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.in28minutes.database.databasedemo.entity.Student;
import com.in28minutes.database.databasedemo.jdbc.StudentJbdcDao;

@ComponentScan("com.in28minutes.database.*")
@SpringBootApplication
public class SpringJdbcTemplateDemoApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentJbdcDao studentJDBCTemplate;
    
	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcTemplateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("------Records Creation--------" );
	      studentJDBCTemplate.create("Zara", 11);
	      studentJDBCTemplate.create("Nuha", 2);
	      studentJDBCTemplate.create("Ayan", 15);

	      System.out.println("------Listing Multiple Records--------" );
	      List<Student> students = studentJDBCTemplate.listStudents();
	      
	      for (Student record : students) {
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Name : " + record.getName() );
	         System.out.println(", Age : " + record.getAge());
	      }  
		
		
	//	logger.info("All users -> {}", studentJDBCTemplate.listStudents());
	}

}
