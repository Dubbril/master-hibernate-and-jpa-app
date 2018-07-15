package com.in28minutes.jpa.hibernate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void all_courses() {

		// 1. Criteria Builder to create a Criteria Query returning the expected result
		// object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder

		// 4.Add Predicated etc the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ", resultList);
	}

	@Test
	public void all_courses_having_100Steps() {

		// 1. Criteria Builder to create a Criteria Query returning the expected result
		// object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Predicate studentIsEmpty = cb.isEmpty(courseRoot.get("students"));

		// 4.Add Predicated etc the Criteria Query
		cq.where(studentIsEmpty);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ", resultList);
	}

	@Test
	public void join() {

		// 1. Criteria Builder to create a Criteria Query returning the expected result
		// object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students");

		// 4.Add Predicated etc the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ", resultList);
	}

	@Test
	public void left_join() {

		// 1. Criteria Builder to create a Criteria Query returning the expected result
		// object
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define Predicates etc using Criteria Builder
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

		// 4.Add Predicated etc the Criteria Query

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Typed Query -> {} ", resultList);
	}

}
