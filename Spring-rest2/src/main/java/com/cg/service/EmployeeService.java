package com.cg.service;

import java.util.List;
import java.util.Optional;

import com.cg.Exceptions.DuplicateEmpIdException;
import com.cg.Exceptions.InvalidEmployeeDataException;
import com.cg.Exceptions.NoSuchEmployeeException;
import com.cg.model.Employee;

public interface EmployeeService {
	public List<Employee> getEmployee();
	
	public int createEmployee(Employee employee) throws DuplicateEmpIdException, InvalidEmployeeDataException, NoSuchEmployeeException;
	
	public Employee readEmployee(int employeeId) throws NoSuchEmployeeException;
	
	public void deleteEmployee(int employeeId) throws NoSuchEmployeeException;
	
	public List<Employee> updateEmployee(Employee employee);
}
