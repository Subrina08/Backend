package com.pass.service;

import java.time.Duration;
import java.time.LocalDateTime;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pass.dto.AttendanceReport;
import com.pass.dto.AttendanceReportDTO;
// import com.pass.exception.ShiftTimingViolationException;
import com.pass.model.Employee;
import com.pass.model.EmployeeAttendance;
import com.pass.repository.EmployeeAttendanceRepository;
import com.pass.repository.EmployeeRepository;

@Service
public class EmployeeAttendanceService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;
    
    
    
    public void calculateHoursWorked(EmployeeAttendance attendance) {
        if (attendance.getInTime() != null && attendance.getOutTime() != null) {
            // Find all EmployeeAttendance records for the same employee and date
            List<EmployeeAttendance> attendancesForDate = attendanceRepository.findByEmployeeAndDate(attendance.getEmployee(), attendance.getDate());

            // Calculate the total minutes worked for that date
            long totalMinutesWorked = 0;
            for (EmployeeAttendance attendanceRecord : attendancesForDate) {
                if (attendanceRecord.getInTime() != null && attendanceRecord.getOutTime() != null) {
                    Duration durationWorked = Duration.between(attendanceRecord.getInTime(), attendanceRecord.getOutTime());
                    totalMinutesWorked += durationWorked.toMinutes();
                }
            }

            // Set the total hours worked for that date
            attendance.setHoursWorked(totalMinutesWorked);
        } else {
            attendance.setHoursWorked(0); // Set hoursWorked to 0 if either inTime or outTime is not set
        }
    }



    public String markAttendance(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new NoSuchElementException("Employee with ID " + empId + " not found."));

        List<EmployeeAttendance> attendances = attendanceRepository.findByEmployeeAndOutTimeIsNull(employee);

        // Find the latest attendance with outTime as NULL (if any)
        EmployeeAttendance existingAttendance = null;
        for (EmployeeAttendance attendance : attendances) {
            if (attendance.getOutTime() == null) {
                existingAttendance = attendance;
                break;
            }
        }

        LocalDateTime currentTime = LocalDateTime.now();

        if (existingAttendance != null) {
            existingAttendance.setOutTime(currentTime);
            calculateHoursWorked(existingAttendance);
            attendanceRepository.save(existingAttendance);
            return "Employee with ID " + empId + " exited.";
        }

        EmployeeAttendance newAttendance = new EmployeeAttendance();
        newAttendance.setEmployee(employee);
        newAttendance.setDate(currentTime.toLocalDate());
        newAttendance.setInTime(currentTime);
        newAttendance.setOutTime(null); // Make sure outTime is initially set to null
        calculateHoursWorked(newAttendance);

        attendanceRepository.save(newAttendance);
        return "Employee with ID " + empId + " entered.";
    }

    
    public EmployeeAttendance createAttendance(EmployeeAttendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public EmployeeAttendance getAttendanceById(Long attendanceId) {
        return attendanceRepository.findById(attendanceId).orElse(null);
    }

    public EmployeeAttendance updateAttendance(EmployeeAttendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendanceById(Long attendanceId) {
        attendanceRepository.deleteById(attendanceId);
    }

    public List <EmployeeAttendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
    
    public Integer getTotalHoursWorked(long empId) {
    	Employee employee = employeeRepository.findById(empId).orElseThrow(NoSuchElementException::new);
    	
    	return attendanceRepository.findTotalHoursWorked(empId);
    }
    
//    public List<AttendanceReport> getAttendanceReport() {
//        return attendanceRepository.getAttendanceReport();
//    }
    
    public List<AttendanceReportDTO> getAttendanceReport() {
        return attendanceRepository.getAttendanceReport();
    }
    

    // ... Other methods as needed
}