package com.in28minutes.jpa.hibernate.app;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.jpa.hibernate.app.entity.FullTimeEmployee;
import com.in28minutes.jpa.hibernate.app.entity.PartTimeEmployee;
import com.in28minutes.jpa.hibernate.app.repository.CourseRepository;
import com.in28minutes.jpa.hibernate.app.repository.EmployeeRepository;
import com.in28minutes.jpa.hibernate.app.repository.StudentRepository;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		 studentRepository.saveStudentWithPassport();
//		 courseRepository.addHardCodeReviewsForCourse();

//		List<Review> reviews = new ArrayList<>();
//		reviews.add(new Review("5", "Great Hand-on Stuff."));
//		reviews.add(new Review("5", "Hatsoff."));

//		courseRepository.addReviewsForCourse(10003L, reviews);

//		studentRepository.insertHardCodeStudentAndCourse();
//		studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices in 100 Steps"));

		// Jack FullTimeEmployeesalary = 10000$
		// Jill PartTimeEmployee = 50$ per hour
		/*employeeRepository.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
		employeeRepository.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));

		logger.info("All FullTimeEmployees -> {} ", employeeRepository.retriveAllFullTimeEmployees());
		logger.info("All PartTimeEmployees -> {} ", employeeRepository.retriveAllPartTimeEmployees());*/
	}
}
