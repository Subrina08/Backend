package com.pass.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeName;
    private String employeeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String project;
    private double hoursWorked;
    private String description;
    private String status;
    
    
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param id
	 * @param employeeName
	 * @param employeeId
	 * @param fromDate
	 * @param toDate
	 * @param project
	 * @param hoursWorked
	 */
	public Timesheet(Long id, String employeeName, String employeeId, LocalDate fromDate, LocalDate toDate,
			String project, double hoursWorked) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.project = project;
		this.hoursWorked = hoursWorked;
	}
	
	
	/**
	 * @param id
	 * @param employeeName
	 * @param employeeId
	 * @param fromDate
	 * @param toDate
	 * @param project
	 * @param hoursWorked
	 * @param description
	 */
	public Timesheet(Long id, String employeeName, String employeeId, LocalDate fromDate, LocalDate toDate,
			String project, double hoursWorked, String description) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.project = project;
		this.hoursWorked = hoursWorked;
		this.description = description;
	}

	public Timesheet() {
        this.status = "Pending";
    }

}
