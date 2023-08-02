package com.pass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pass.model.Employee;
import com.pass.model.EmployeeAttendance;
import com.pass.model.EmployeeTotalMinutes;
import com.pass.repository.EmployeeAttendanceRepository;
import com.pass.repository.EmployeeTotalMinutesRepository;

import java.util.List;

@Service
public class EmployeeTotalMinutesService {

    private final EmployeeAttendanceRepository attendanceRepository;
    private final EmployeeTotalMinutesRepository totalMinutesRepository;

    @Autowired
    public EmployeeTotalMinutesService(EmployeeAttendanceRepository attendanceRepository,
                                       EmployeeTotalMinutesRepository totalMinutesRepository) {
        this.attendanceRepository = attendanceRepository;
        this.totalMinutesRepository = totalMinutesRepository;
    }

    public void calculateTotalMinutesForEmployee(Long empId) {
        Employee employee = new Employee();
        employee.setAccessCardId(empId);

        List<EmployeeAttendance> attendances = attendanceRepository.findByEmployee(employee);

        long totalMinutes = 0;
        for (EmployeeAttendance attendance : attendances) {
            // Assuming hoursWorked is in minutes
            totalMinutes += attendance.getHoursWorked();
        }

        EmployeeTotalMinutes totalMinutesRecord = new EmployeeTotalMinutes();
        totalMinutesRecord.setEmployee(employee);
        totalMinutesRecord.setTotalMinutes(totalMinutes);

        totalMinutesRepository.save(totalMinutesRecord);
    }
}
