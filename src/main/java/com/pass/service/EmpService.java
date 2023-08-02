package com.pass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pass.model.Emp;
import com.pass.model.Employee;
import com.pass.repository.EmpRepository;

@Service
public class EmpService {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmployeeService employeeService;

    public Emp insert(Emp emp) {
      
        Emp savedEmp = empRepository.save(emp);

  
        Employee employee = new Employee();
        employee.setName(savedEmp.getName());
        employee.setLocation(savedEmp.getAddress());

        employeeService.addEmployee(employee);

        return savedEmp;
    }
}



