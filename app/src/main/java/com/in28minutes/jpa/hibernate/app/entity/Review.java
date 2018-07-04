package com.in28minutes.jpa.hibernate.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue
	private Long id;

	private String description;
	private String rating;

	public Review() {

	}

	public Review(String rating, String descript) {
		this.rating = rating;
		this.description = descript;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("Review [id=%s, description=%s]", id, description);
	}

}
