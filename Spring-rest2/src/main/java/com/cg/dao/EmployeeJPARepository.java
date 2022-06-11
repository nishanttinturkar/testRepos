package com.cg.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Employee;

@Repository
public interface EmployeeJPARepository extends JpaRepository<Employee,Integer> {
	
		}
		 

