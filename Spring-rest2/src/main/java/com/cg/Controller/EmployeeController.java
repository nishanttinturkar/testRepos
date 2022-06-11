package com.cg.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Exceptions.InvalidEmployeeDataException;
import com.cg.Exceptions.NoSuchEmployeeException;
import com.cg.Exceptions.DuplicateEmpIdException;
import com.cg.model.Employee;
import com.cg.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeService emps;

	public EmployeeController(EmployeeService emps) {
		super();
		this.emps = emps;
	}

	@RequestMapping(value = "/getEmp", produces = "application/json")
	public List<Employee> getAllEmployee() {
		List<Employee> emp = emps.getEmployee();
		return emp;
	}

	@PostMapping(value = "saveemp", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws DuplicateEmpIdException,  InvalidEmployeeDataException, NoSuchEmployeeException {
		
		int id = emps.createEmployee(employee);
		if (id >0)
		{
			return ResponseEntity.ok(employee);
		}
		throw new DuplicateEmpIdException("id exist");
	}

	@PutMapping(path = "{empId}", consumes = "application/json")
	public ResponseEntity<List<Employee>> updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee) {
		List<Employee> employees = emps.updateEmployee(employee);
		if(employees.isEmpty())
		{
			return new ResponseEntity("Sorry! Employee not available!", 
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}

	@DeleteMapping(path = "{empId}", produces = "application/json")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("empId") int empId) throws NoSuchEmployeeException {
		
		Employee result = emps.readEmployee(empId);
		ResponseEntity<Employee> response = null;
		if (result.getEmployeeId() > 0) {
			System.out.println("Inside if");
			emps.deleteEmployee(empId);
			response = new ResponseEntity<Employee>(result, HttpStatus.OK);
			//]ResponseEntity response = new ResponseEntity("employee deleted successfully", HttpStatus.ACCEPTED);
			return response;
		} else {
			System.out.println("Inside else exception");
			throw new NoSuchEmployeeException("Employee with this ID Not Found");
		}
	}
}

/*
 * public class EmployeeController {
 * 
 * @Autowired private EmpService empservice;
 * 
 * @GetMapping(value = "/gettempdatails",produces ="application/json")
 * List<Employee> getEmployees(){ List<Employee>
 * empList=empservice.getAllEmployees(); return empList; }
 * 
 * @PostMapping(value = "/savemap",consumes ="application/json",produces =
 * "application/json") ResponseEntity<Employee> createEmployee(@RequestBody
 * Employee e){ int id=empservice.createEmployee(e);
 * System.out.println("Employee with id "+id+" has been created"); return
 * ResponseEntity.ok(e); }
 * 
 * }
 */