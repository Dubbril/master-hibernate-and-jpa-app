package com.in28minutes.jpa.hibernate.app.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.in28minutes.jpa.hibernate.app.AppApplication;
import com.in28minutes.jpa.hibernate.app.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository repository;

	@Test
	public void findById_CoursePresent() {
		Optional<Course> courseOptional = repository.findById(10001L);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void findById_CourseNotPresent() {
		Optional<Course> courseOptional = repository.findById(20001L);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	public void playingArondWithSpringDataRepository() {
		/*
		 * Course course = new Course("Microservices in 100 Steps");
		 * repository.save(course);
		 * course.setName("Microservices in 100 Steps - Updated");
		 * repository.save(course);
		 */

		logger.info("Course -> {} ", repository.findAll());
		logger.info("Count -> {} ", repository.count());
	}

	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		logger.info("Sorted Course -> {} ", repository.findAll(sort));
//		[Course [id=10001, name=JPA in 50 Steps], Course [id=10002, name=Spring in 50 Steps], Course [id=10003, name=Spring boot in 100 Steps]]
//		[Course [id=10002, name=Spring in 50 Steps], Course [id=10003, name=Spring boot in 100 Steps], Course [id=10001, name=JPA in 50 Steps]]
//		[Course [id=10001, name=JPA in 50 Steps], Course [id=10003, name=Spring boot in 100 Steps], Course [id=10002, name=Spring in 50 Steps]] 
		logger.info("Count -> {} ", repository.count());
	}

	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page -> {} ", firstPage.getContent());
//		[Course [id=10001, name=JPA in 50 Steps], Course [id=10002, name=Spring in 50 Steps], Course [id=10003, name=Spring boot in 100 Steps]]
//		[Course [id=10004, name=Dummy1], Course [id=10005, name=Dummy2], Course [id=10006, name=Dummy3]] 
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);
		logger.info("Second Page -> {} ", secondPage.getContent());

	}

	@Test
	public void findUsingName() {
		logger.info("FindByName -> {} ", repository.findByName("JPA in 50 Steps"));
	}
}
