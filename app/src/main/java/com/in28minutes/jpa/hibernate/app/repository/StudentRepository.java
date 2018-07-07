package com.in28minutes.jpa.hibernate.app.repository;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.app.entity.Passport;
import com.in28minutes.jpa.hibernate.app.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}
		return student;
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}

	public void sumOperationToUnderstandPersistanceContext() {
		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);

		// Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();

		// Database Operation 3 - Update passport
		passport.setNumber("E123457");

		// Database Operation 4 - Update student
		student.setName("Ranga - updated");
	}

}
