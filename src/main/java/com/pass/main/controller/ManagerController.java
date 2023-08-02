package com.pass.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pass.model.Manager;
import com.pass.model.User;
import com.pass.service.ManagerService;
import com.pass.service.MyUserService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyUserService userService; 
	@PostMapping("/add")
	public Manager postManager(@RequestBody Manager manager) {
		
		User user = manager.getUser();
		user.setRole("MANAGER");
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		user = userService.insert(user);
		
	
		manager.setUser(user);
		
		
		return managerService.insert(manager);
	}
}
