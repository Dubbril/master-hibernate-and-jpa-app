package com.in28minutes.jpa.hibernate.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa.hibernate.app.entity.Course;
import com.in28minutes.jpa.hibernate.app.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}

	// retrieve all employees
	public List<Employee> retriveAllEmployees() {
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
	}

}
