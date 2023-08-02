package com.pass.main.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pass.model.User;
import com.pass.repository.UserRepository;
import com.pass.service.MyUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MyUserService userService; 
	
	@Autowired
    private UserRepository userRepository;
	
	@GetMapping("/login") //by the time we reach here, spring has already verified username/password
	public UserDetails login(Principal principal) { //injecting principal in method
		 //I need username from spring, so that i can go to db and fetch role and give to UI dev. 
	
		/* An Interface called as 'Principal' in Spring security can help us read username */
		String username = principal.getName();
		UserDetails user = userService.loadUserByUsername(username);
		return user; 
	}
	
	@GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


