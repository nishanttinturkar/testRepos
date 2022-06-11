package com.cg.dao;

import java.util.List;

import com.cg.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployee();
	
	public int createEmployee(Employee employee);
	
	public Employee readEmployee(int employeeId);
	
	public int deleteEmployee(int employeeId);
	
	public int updateEmployee(Employee employee);

}
