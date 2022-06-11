package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.Exceptions.DuplicateEmpIdException;
import com.cg.Exceptions.InvalidEmployeeDataException;
import com.cg.Exceptions.NoSuchEmployeeException;
import com.cg.dao.EmployeeJPARepository;
import com.cg.model.Employee;

@Service("service")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	// EmployeeDao dao;
	EmployeeJPARepository empRepo;

	@Override
	public List<Employee> getEmployee() {
		// return dao.getEmployee();
		return empRepo.findAll();
	}

	@Override
	public Employee readEmployee(int employeeId) throws NoSuchEmployeeException {
//		Employee result = dao.readEmployee(employeeId);
		Optional<Employee> result = empRepo.findById(employeeId);
		Employee emp = result.orElse(null);
		if (emp != null) {
			return emp;
		} else {
			throw new NoSuchEmployeeException("No employee present with Employee Id: " + employeeId);
		}
	}

	@Override
	public void deleteEmployee(int employeeId) throws NoSuchEmployeeException {
		// int empid = dao.deleteEmployee(employeeId);
		Employee result = readEmployee(employeeId);
		if (result != null) {
			empRepo.deleteById(employeeId);
		} else {
			throw new NoSuchEmployeeException("No employee present with Employee Id: " + employeeId);
		}

	}

//@Override
	public int createEmployee(Employee employee) throws InvalidEmployeeDataException, DuplicateEmpIdException {
		System.out.println("create employee called");
		System.out.println("employeeid " + employee.getEmployeeId());
		Employee emp;
		try {
			emp = readEmployee(employee.getEmployeeId());
			System.out.println("Duplicate id");
			if (emp.getEmployeeId() == employee.getEmployeeId()) {
				throw new DuplicateEmpIdException("Employee with " + employee.getEmployeeId() + " already Exist");
			}
			if (employee.getEmployeeId() <= 0) {
				throw new InvalidEmployeeDataException("Employee ID is Invalid");
			}
			return 0;
		} catch (NoSuchEmployeeException e1) {
			Employee e = empRepo.save(employee);
			return e.getEmployeeId();
		}
	}

//	@Override
	public List<Employee> updateEmployee(Employee employee) {
//		return dao.updateEmployee(employee);
		empRepo.saveAndFlush(employee);
		return empRepo.findAll();
	}

}
