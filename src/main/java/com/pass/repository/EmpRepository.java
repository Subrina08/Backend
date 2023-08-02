package com.pass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pass.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{

	

}
