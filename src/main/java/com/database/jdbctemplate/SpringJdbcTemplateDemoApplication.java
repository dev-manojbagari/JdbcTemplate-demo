package com.database.jdbctemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.in28minutes.database.databasedemo.entity.Student;
import com.in28minutes.database.databasedemo.jdbc.EmployeeJbdcDao;
import com.in28minutes.database.databasedemo.jdbc.PersonJbdcDao;
import com.in28minutes.database.databasedemo.jdbc.StudentJbdcDao;

@ComponentScan("com.in28minutes.database.*")
@SpringBootApplication
public class SpringJdbcTemplateDemoApplication implements CommandLineRunner {

	@Autowired
	StudentJbdcDao studentJDBCTemplate;

	@Autowired
	EmployeeJbdcDao employeeJDBCTemplate;

	@Autowired
	PersonJbdcDao personJDBCTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcTemplateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println("------Records Creation--------" );
//	      studentJDBCTemplate.create("Zara", 11);
//	      studentJDBCTemplate.create("Nuha", 2);
//	      studentJDBCTemplate.create("Ayan", 15);
//
//	      System.out.println("------Listing Multiple Records--------" );
//	      List<Student> students = studentJDBCTemplate.findAll();
//	      
//	      for (Student record : students) {
//	         System.out.print("ID : " + record.getId() );
//	         System.out.print(", Name : " + record.getName() );
//	         System.out.println(", Age : " + record.getAge());
//	      }  
//		
//	      System.out.println("----Updating Record with ID = 2 -----" );
//	      studentJDBCTemplate.update(2, 20);
//	      
//	      System.out.println("----Listing Record with ID = 20 -----" );
//	      Student student = studentJDBCTemplate.findById(20);
//	      System.out.print("ID : " + student.getId() );
//	      System.out.print(", Name : " + student.getName() );
//	      System.out.println(", Age : " + student.getAge());  
//	      
//	      
//	      System.out.println("----Delete Record with ID = 2 -----" );
//	      studentJDBCTemplate.delete(2);
//
//	      System.out.println("------Listing Multiple Records--------" );
//	      List<Student> students2 = studentJDBCTemplate.findAll();
//	      
//	      for (Student record : students2) {
//	         System.out.print("ID : " + record.getId() );
//	         System.out.print(", Name : " + record.getName() );
//	         System.out.println(", Age : " + record.getAge());
//	      }     
//	      System.out.println("------Calling Stored Procedure--------" );
//
//	      Student student3 = studentJDBCTemplate.getStudentByStoredProcedure(1);
//	      System.out.print("ID : " + student3.getId() );
//	      System.out.print(", Name : " + student3.getName() );
//	      System.out.println(", Age : " + student3.getAge()); 
//	    
//	      
//	      System.out.println("------Calling Stored Function--------" );
//	      Student student4 = studentJDBCTemplate.getStudentByStoredFunction(1);
//	      System.out.print("ID : " + student4.getId() );
//	      System.out.print(", Name : " + student4.getName() );
//	      

//		byte[] imageData = { 0, 1, 0, 8, 20, 40, 95 };
//		employeeJDBCTemplate.updateImage(1, imageData);

// personJDBCTemplate.updateDescription(1, "This can be a very long text upto 4 GB of size.");

//		System.out.println("------Batch update--------");
//		List<Student> initialStudents = studentJDBCTemplate.findAll();
//		System.out.println("Initial Students");
//
//		for (Student student2 : initialStudents) {
//			System.out.print("ID : " + student2.getId());
//			System.out.println(", Age : " + student2.getAge());
//		}
//		Student student = new Student();
//		student.setId(1);
//		student.setAge(10);
//
//		Student student1 = new Student();
//		student1.setId(3);
//		student1.setAge(10);
//
//		List<Student> students = new ArrayList<Student>();
//		students.add(student);
//		students.add(student1);
//
//		studentJDBCTemplate.batchUpdate(students);
//
//		List<Student> updatedStudents = studentJDBCTemplate.findAll();
//		System.out.println("Updated Students");
//
//		for (Student student3 : updatedStudents) {
//			System.out.print("ID : " + student3.getId());
//			System.out.println(", Age : " + student3.getAge());
//		}

		List<Student> initialStudents = studentJDBCTemplate.findAll();
		System.out.println("Initial Students");

		for (Student student2 : initialStudents) {
			System.out.print("ID : " + student2.getId());
			System.out.println(", Age : " + student2.getAge());
		}
		Student student = new Student();
		student.setId(1);
		student.setAge(15);

		Student student1 = new Student();
		student1.setId(3);
		student1.setAge(16);

		List<Student> students = new ArrayList<Student>();
		students.add(student);
		students.add(student1);

		studentJDBCTemplate.batchUpdateUsingObjects(students);

		List<Student> updatedStudents = studentJDBCTemplate.findAll();
		System.out.println("Updated Students");

		for (Student student3 : updatedStudents) {
			System.out.print("ID : " + student3.getId());
			System.out.println(", Age : " + student3.getAge());
		}

		// logger.info("All users -> {}", studentJDBCTemplate.listStudents());
	}

}
