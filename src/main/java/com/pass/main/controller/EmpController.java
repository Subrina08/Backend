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
		/* Fetch the user from emp */
		User user = emp.getUser();
		
		/* Encode the password given as Plain text from UI */
		user.setPassword(encoder.encode(user.getPassword()));
		
		/* Set the role: EMPLOYEE */
		user.setRole("EMPLOYEE");
		
		/* Save the user in DB */
		user  = userService.insert(user);
		return empService.insert(emp);

	}


 

//	
//	@Autowired
//	private ManagerService managerService; 
//	
//	
//	public ResponseEntity<?> addEmp(@PathVariable("managerId") int managerId, 
//			@RequestBody Emp emp) {
//		/* validate managerId and fetch manager obj from DB */
//		Manager manager  = managerService.getById(managerId);
//		if(manager == null)
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body("Manager ID invalid");
//		
//		/* attach manager to emp */
//		emp.setManager(manager);
//		
//		/* Fetch the user from emp */
//		User user = emp.getUser();
//		
//		/* Encode the password given as Plain text from UI */
//		user.setPassword(encoder.encode(user.getPassword()));
//		
//		/* Set the role: EMPLOYEE */
//		user.setRole("EMPLOYEE");
//		
//		/* Save the user in DB */
//		user  = userService.insert(user);
		
		/* Attach user to emp and save emp */
//		emp.setUser(user);
//		return ResponseEntity.status(HttpStatus.OK)
//				.body(emp);
	}
	


		



