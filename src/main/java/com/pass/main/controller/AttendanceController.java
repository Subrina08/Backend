package com.pass.main.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pass.dto.AttendanceReport;
import com.pass.dto.AttendanceReportDTO;
//import com.pass.exception.ShiftTimingViolationException;
import com.pass.model.EmployeeAttendance;
import com.pass.service.EmployeeAttendanceService;

@RestController
@RequestMapping("/attendance")

@CrossOrigin(origins = "http://localhost:3003/Attendance")
public class AttendanceController {

    @Autowired
    private EmployeeAttendanceService attendanceService;

    @PostMapping("/{empId}")
    public ResponseEntity<String> markAttendance(@PathVariable Long empId) {
        try {
            String responseMessage = attendanceService.markAttendance(empId);
            return ResponseEntity.ok(responseMessage);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
        }
    }
    

    
    @GetMapping("/{attendanceId}")
    public ResponseEntity<EmployeeAttendance> getAttendanceById(@PathVariable Long attendanceId) {
        EmployeeAttendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance != null) {
            return ResponseEntity.ok(attendance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{attendanceId}")
    public ResponseEntity<EmployeeAttendance> updateAttendance(@PathVariable Long attendanceId, @RequestBody EmployeeAttendance updatedAttendance) {
        EmployeeAttendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance != null) {
           
            attendance.setInTime(updatedAttendance.getInTime());
            attendance.setOutTime(updatedAttendance.getOutTime());
            

            EmployeeAttendance updated = attendanceService.updateAttendance(attendance);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{attendanceId}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long attendanceId) {
        EmployeeAttendance attendance = attendanceService.getAttendanceById(attendanceId);
        if (attendance != null) {
            attendanceService.deleteAttendanceById(attendanceId);
            return ResponseEntity.ok("Attendance with ID " + attendanceId + " has been deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeAttendance>> getAllAttendances() {
        List<EmployeeAttendance> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }
    
    @GetMapping("/totalHours/{empId}")
    public ResponseEntity<?> getTotalHoursWorked(@PathVariable long empId) {
        try {
            Integer totalHours = attendanceService.getTotalHoursWorked(empId);
            return ResponseEntity.ok("Total hours worked: " + totalHours);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + empId + " not found.");
        }
    }

    @GetMapping("/report")
    public ResponseEntity<List<AttendanceReportDTO>> getAttendanceReport() {
        List<AttendanceReportDTO> report = attendanceService.getAttendanceReport();
        return ResponseEntity.ok(report);
    }
    
}
