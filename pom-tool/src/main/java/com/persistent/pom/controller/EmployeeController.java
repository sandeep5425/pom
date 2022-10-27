package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.config.JwtToken;
import com.persistent.pom.customexception.EntityAlreadyExistException;
import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.entities.Employee;
import com.persistent.pom.response.ErrorMessage;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	JwtToken jwtToken;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@GetMapping(value = "/employees")
	public ResponseEntity<ResponseMessage<List<Employee>>> getEmployees() throws IllegalArgumentException, Exception {
		LOGGER.info("Entered Employee controller to get employees list :: method = getEmployees");
		// String token = request.getHeader("Token");

		// String username = jwtToken. ;

		// token -> validated (in order to get roles)

		// check token is valid and then also

		// if the user admin we need throw an exception
//		getClaims(Token token)

		List<Employee> employees = employeeService.getEmployees();
		ResponseMessage<List<Employee>> responseMessage = new ResponseMessage<>();
		if (employees.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No employees found");
			LOGGER.error("Exit Employee controller with no employees found :: method = getEmployees ");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(employees.size());
		responseMessage.setMessage("List of employees records that are found");
		responseMessage.setData(employees);
		LOGGER.info("Exit Employee controller by fetching list of employees :: method = getEmployees ");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> getParticularEmployee(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Employee controller to find employee with their id:: method = getEmployee");
		Optional<Employee> optEmployee = employeeService.getEmployee(id);
		if (!optEmployee.isPresent()) {
			LOGGER.info("Exit Employee controller with no such id found :: method = getEmployee");
			throw new NoSuchIDException();
		}
		Employee employee = optEmployee.get();
		ResponseMessage<Employee> response = new ResponseMessage<>();
		response.setData(employee);
		response.setLength(1);
		response.setMessage("Details of the employee with id : " + id);
		LOGGER.info("Exit Employee controller finding employee with given id :: method = getEmployee");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> addEmployee(@RequestBody Employee emp)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered Employee controller to add an employee:: method = addEmployee");
		Optional<Employee> optEmployee = employeeService.getEmployee(emp.getId());
		if (optEmployee.isPresent()) {
			LOGGER.info("Exit Employee controller as same employee exists :: method = addEmployee ");
			throw new EntityAlreadyExistException();
		}
		Employee employee = employeeService.addEmployee(emp);
		ResponseMessage<Employee> response = new ResponseMessage<>();
		response.setData(employee);
		response.setMessage("Employee data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Employee controller after adding employee :: method = addEmployee ");
		return new ResponseEntity<ResponseMessage<Employee>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> updateEmployee(@RequestBody Employee emp)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Employee controller to update an employee :: method = updateEmployee ");
		Optional<Employee> optEmployee = employeeService.getEmployee(emp.getId());
		if (!optEmployee.isPresent()) {
			LOGGER.error("Exit Employee controller as no employee exists with that id:: method = updateEmployee ");
			throw new NoSuchIDException();
		}
		Employee employee = optEmployee.get();
		ResponseMessage<Employee> response = new ResponseMessage<>();
		response.setData(employee);
		response.setMessage("Data after updating the employee");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Employee controller after updating employee :: method = updateEmployee ");
		return new ResponseEntity<ResponseMessage<Employee>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> deleteEmployee(@RequestParam int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Employee controller to delete an employee :: method = deleteEmployee ");
		Optional<Employee> optEmployee = employeeService.getEmployee(id);
		if (!optEmployee.isPresent()) {
			LOGGER.info("Exit Employee controller as no employee exists with that id:: method = deleteEmployee ");
			throw new NoSuchIDException();
		}
		// just put the isActive to false..
		employeeService.deleteEmployee(id);
		ResponseMessage<Employee> response = new ResponseMessage<>();
		response.setData(optEmployee.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit Employee controller after deleting employee sucessfully:: method = deleteEmployee ");
		return new ResponseEntity<ResponseMessage<Employee>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/employeeByName")
	public Employee getEmployeeByName(@RequestParam("email") String email) {
		return employeeService.getEmployeeByEmail(email);
	}

}
