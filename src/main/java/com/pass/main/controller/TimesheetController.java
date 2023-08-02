package com.pass.main.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pass.model.Timesheet;
import com.pass.service.TimesheetService;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    // Endpoint to create a new timesheet
    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    // Endpoint to update an existing timesheet by ID
    @PutMapping("/{id}")
    public Timesheet updateTimesheet(@PathVariable Long id, @RequestBody Timesheet updatedTimesheet) {
        return timesheetService.updateTimesheet(id, updatedTimesheet);
    }

    // Endpoint to delete a timesheet by ID
    @DeleteMapping("/{id}")
    public void deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
    }

    // Endpoint to get a timesheet by ID
    @GetMapping("/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id) {
        return timesheetService.getTimesheetById(id);
    }

    // Endpoint to get all timesheets
    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetService.getAllTimesheets();
    }

    // Endpoint to approve a timesheet by ID
    @PutMapping("/{id}/approve")
    public Timesheet approveTimesheet(@PathVariable Long id) {
        return timesheetService.updateTimesheetStatus(id, "Approved");
    }

    // Endpoint to reject a timesheet by ID
    @PutMapping("/{id}/reject")
    public Timesheet rejectTimesheet(@PathVariable Long id) {
        return timesheetService.updateTimesheetStatus(id, "Rejected");
    }
    
   
}
    

