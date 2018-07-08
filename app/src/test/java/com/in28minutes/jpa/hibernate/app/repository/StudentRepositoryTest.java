package com.in28minutes.jpa.hibernate.app.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.app.AppApplication;
import com.in28minutes.jpa.hibernate.app.entity.Passport;
import com.in28minutes.jpa.hibernate.app.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EntityManager em;

	@Test
	public void someTest() {
		studentRepository.sumOperationToUnderstandPersistanceContext();
	}

	@Test
	@Transactional
	public void retrievesStudentAndPassportDetails() {
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {} ", student);
		logger.info("passport -> {} ", student.getPassport());

	}

	@Test
	@Transactional
	public void retrievesPassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {} ", passport);
		logger.info("passport -> {} ", passport.getStudent());

	}

}
