package com.pass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pass.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer>{

}
