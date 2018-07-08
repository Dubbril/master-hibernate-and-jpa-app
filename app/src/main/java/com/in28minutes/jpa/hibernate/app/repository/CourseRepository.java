package com.in28minutes.jpa.hibernate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.app.entity.Course;
import com.in28minutes.jpa.hibernate.app.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public Course findById(Long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}

	public void playWithEntityManager() {
		Course course1 = new Course("Web Service in 100 Steps");
		em.persist(course1);

		Course course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");
	}

	public void addHardCodeReviewsForCourse() {
		// get the course 10003
		Course course = findById(10003L);
		logger.info("course.getReviews() -> {} ", course.getReviews());

		// add 2 review to it
		Review review1 = new Review("5", "Great Hand-on Stuff.");
		Review review2 = new Review("5", "Hatsoff.");

		course.addReviews(review1);
		review1.setCourse(course);

		course.addReviews(review2);
		review2.setCourse(course);

		// save it to database

		em.persist(review1);
		em.persist(review2);

	}

	public void addReviewsForCourse(Long courseId, List<Review> reviews) {
		Course course = findById(courseId);
		logger.info("course.getReviews() -> {} ", course.getReviews());

		for (Review review : reviews) {
			
			// Setting the relationship
			course.addReviews(review);
			review.setCourse(course);
			em.persist(review);
		}

	}

}
