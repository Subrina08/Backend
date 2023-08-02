package com.pass.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pass.model.Emp;
import com.pass.model.Manager;
import com.pass.model.User;
import com.pass.service.EmpService;
import com.pass.service.ManagerService;
import com.pass.service.MyUserService;

@RestController
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private PasswordEncoder encoder; 
	
	@Autowired
	private EmpService empService; 
	@Autowired
	private MyUserService userService;
	@PostMapping("/add")
	public Emp addEmp(@RequestBody Emp emp) {
		
		User user = emp.getUser();
		
	
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		user.setRole("EMPLOYEE");
		
	
		user  = userService.insert(user);
		return empService.insert(emp);

	}

}