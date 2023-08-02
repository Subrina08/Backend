package com.pass.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pass.model.Employee;
import com.pass.model.Manager;
import com.pass.repository.ManagerRepository;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
    private EmployeeService employeeService;
	
	
	public Manager insert(Manager manager) {
	
        Employee employee = new Employee();
        employee.setName(manager.getName());
        employee.setLocation(manager.getAddress());

        employeeService.addEmployee(employee);
        
		return managerRepository.save(manager);
		
		
	}

	public Manager getById(int managerID) {
		Optional<Manager> optional = managerRepository.findById(managerID);
		
		if(!optional.isPresent())
			return null;
		
		return optional.get();
	}
	
	

}
