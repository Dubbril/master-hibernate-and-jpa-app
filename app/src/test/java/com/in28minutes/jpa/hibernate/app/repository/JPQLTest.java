package com.in28minutes.jpa.hibernate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.app.AppApplication;
import com.in28minutes.jpa.hibernate.app.entity.Course;
import com.in28minutes.jpa.hibernate.app.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createNamedQuery("query_get_all_courses");
		List resultList = query.getResultList();
		logger.info("Select c From Course c -> {} ", resultList);
	}

	@Test
	public void jpql_type() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c -> {} ", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = em.createNamedQuery("query_get_100_steps_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c From Course c where name like '%100 Steps' -> {} ", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {} ", resultList);
	}

	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {} ", resultList);
	}

	@Test
	public void jpql_courses_with_order_by_students() {
		TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc",
				Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {} ", resultList);
	}

	@Test
	public void jpql_courses_with_passport_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%' ",
				Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {} ", resultList);
	}

	@Test
	public void join() {
		Query query = em.createQuery("Select c, s from Course c Join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void left_join() {
		Query query = em.createQuery("Select c, s from Course c LEFT Join c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
	
	@Test
	public void cross_join() {
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Result Size -> {}", resultList.size());
		for (Object[] result : resultList) {
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

}
