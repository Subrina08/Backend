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


    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

   
    @PutMapping("/{id}")
    public Timesheet updateTimesheet(@PathVariable Long id, @RequestBody Timesheet updatedTimesheet) {
        return timesheetService.updateTimesheet(id, updatedTimesheet);
    }

    
    @DeleteMapping("/{id}")
    public void deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
    }

    @GetMapping("/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id) {
        return timesheetService.getTimesheetById(id);
    }


    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetService.getAllTimesheets();
    }


    @PutMapping("/{id}/approve")
    public Timesheet approveTimesheet(@PathVariable Long id) {
        return timesheetService.updateTimesheetStatus(id, "Approved");
    }


    @PutMapping("/{id}/reject")
    public Timesheet rejectTimesheet(@PathVariable Long id) {
        return timesheetService.updateTimesheetStatus(id, "Rejected");
    }
    
   
}
    

