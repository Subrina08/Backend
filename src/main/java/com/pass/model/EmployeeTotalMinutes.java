package com.pass.model;

import javax.persistence.*;

@Entity
@Table(name = "employee_total_minutes")
public class EmployeeTotalMinutes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Column(name = "total_minutes")
    private long totalMinutes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(long totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	

	/**
	 * @param id
	 * @param employee
	 * @param totalMinutes
	 */
	public EmployeeTotalMinutes(Long id, Employee employee, long totalMinutes) {
		
		this.id = id;
		this.employee = employee;
		this.totalMinutes = totalMinutes;
	}

	public EmployeeTotalMinutes() {
		// TODO Auto-generated constructor stub
	}

	
	
	

    // Getters and setters, constructors, etc.
    
}

