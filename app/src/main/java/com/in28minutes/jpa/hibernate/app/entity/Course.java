package com.in28minutes.jpa.hibernate.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries(value = { 
		@NamedQuery(name = "query_get_all_courses", query = "Select c From Course c"),
		@NamedQuery(name = "query_get_all_courses_join_fetch", query = "Select c From Course c join fetch c.students s"),
		@NamedQuery(name = "query_get_100_steps_courses", query = "Select c From Course c where name like '%100 Steps'") 
		})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=? ")
@Where(clause = "is_deleted = false")
public class Course {

	private static Logger LOGGER = LoggerFactory.getLogger(Course.class);

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "course")
	private List<Review> reviews = new ArrayList<>();

	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	private LocalDateTime createdDate;

	private boolean isDeleted;

	@PreRemove
	private void preRemove() {
		LOGGER.info("Setting isDeleted to True");
		this.isDeleted = true;
	}

	public Course() {

	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReviews(Review reviews) {
		this.reviews.add(reviews);
	}

	public void removeReviews(Review reviews) {
		this.reviews.remove(reviews);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	@Override
	public String toString() {
		return String.format("Course [%s] Review[%s]", name, reviews);
	}

}
