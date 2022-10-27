package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.config.JwtToken;
import com.persistent.pom.config.TokenResponse;
import com.persistent.pom.config.UserRequest;
import com.persistent.pom.converters.EntityToDto;
import com.persistent.pom.dto.EmployeeDto;
import com.persistent.pom.entities.Employee;
import com.persistent.pom.entities.Roles;
import com.persistent.pom.entities.Skill;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.EmployeeService;

@RestController
public class AuthorizeUserController {

	@Autowired
	JwtToken jwtToken;

	@Autowired
	EntityToDto entityToDto;

	@Autowired
	EmployeeService employeeService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeUserController.class);

	@Value("#{'${pom.admin.list}'.split(',')}")
	private List<String> admins;

	@PostMapping(value = "/authorize")
	public ResponseEntity<ResponseMessage<List<EmployeeDto>>> authorize(@RequestBody UserRequest user)
			throws Exception {
		LOGGER.info("Entered Authorize user controller :: method = authorize");
		ResponseMessage<List<EmployeeDto>> response = new ResponseMessage<>();
		HashMap<String, String> claims = new HashMap<>();
		String username = user.getUsername();
		List<Employee> employees = null;
		if (admins.contains(username)) {
			LOGGER.info("Got the employee role as admin");
			claims.put("ROLE", "ADMIN");
			response.setMessage("An admin user logged");
			employees = employeeService.getEmployees();
			List<EmployeeDto> employeesDto = new ArrayList<>();
			for (Employee eachEmployee : employees) {
				employeesDto.add(entityToDto.employee(eachEmployee));
			}
			response.setData(employeesDto);
			response.setLength(employeesDto.size());
		} else {

			Employee employee = employeeService.getEmployeeByEmail(username + "@g");
			int roleId = employee.getRole().getId();
			if (roleId == 2) {
				LOGGER.info("A lead user logged");
				claims.put("ROLE", "LEAD");
				employees = employeeService.getEmployeeByProject(employee.getProject());
				List<EmployeeDto> employeesDto = new ArrayList<>();
				for (Employee eachEmployee : employees) {
					employeesDto.add(entityToDto.employee(eachEmployee));
				}
				response.setData(employeesDto);
				response.setLength(employeesDto.size());
			} else if (roleId == 3) {
//				A developer logged in 
				LOGGER.info("A developer logged");
				Optional<Employee> developer = employeeService.getEmployee(employee.getId());
				EmployeeDto developerDto = entityToDto.employee(developer.get());
				ArrayList<EmployeeDto> result = new ArrayList<>();
				result.add(developerDto);
				response.setData(result);

			} else {
				// A member with invalid role
				System.out.println("Invalid role logged");
			}

		}

		String token = jwtToken.generateJWTToken(claims, user);

		response.setStatusCode(HttpStatus.OK);
		response.setToken(token);
		return new ResponseEntity<ResponseMessage<List<EmployeeDto>>>(response, HttpStatus.OK);
	}

}
