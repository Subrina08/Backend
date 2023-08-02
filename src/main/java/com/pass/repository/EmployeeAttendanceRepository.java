package com.pass.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pass.dto.AttendanceReport;
import com.pass.dto.AttendanceReportDTO;
import com.pass.model.Employee;
import com.pass.model.EmployeeAttendance;
@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    List<EmployeeAttendance> findByEmployeeAndOutTimeIsNull(Employee employee);

	List<EmployeeAttendance> findByEmployee(Employee employee);

	@Query("select SUM(ea.hoursWorked) from EmployeeAttendance ea where ea.employee.accessCardId=?1")
	Integer findTotalHoursWorked(long empId);
	
	

	List<EmployeeAttendance> findByEmployeeAndDate(Employee employee, LocalDate date);
	
	@Query(value = "SELECT e.id, e.name, e.location, ea.hours_worked AS hoursWorked, ea.date " +
            "FROM employees e " +
            "JOIN (SELECT emp_id, MAX(in_time) AS latest_in_time FROM employee_attendance GROUP BY emp_id, date) latest_attendance " +
            "ON e.id = latest_attendance.emp_id " +
            "JOIN employee_attendance ea " +
            "ON e.id = ea.emp_id AND latest_attendance.latest_in_time = ea.in_time " +
            "ORDER BY e.id, ea.date",
    nativeQuery = true)
List<AttendanceReportDTO> getAttendanceReport();
	
	
}




