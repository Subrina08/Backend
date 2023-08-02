package com.pass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pass.model.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    // Add any custom queries if needed
	
	
}

