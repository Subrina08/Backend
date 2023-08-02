package com.pass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pass.exception.NotFoundException;
import com.pass.model.Timesheet;
import com.pass.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;

    @Autowired
    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
      

        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Long id, Timesheet updatedTimesheet) {
        Optional<Timesheet> optionalTimesheet = timesheetRepository.findById(id);
        if (optionalTimesheet.isEmpty()) {
            throw new NotFoundException("Timesheet not found with id: " + id);
        }

        Timesheet existingTimesheet = optionalTimesheet.get();

     
        existingTimesheet.setEmployeeName(updatedTimesheet.getEmployeeName());
        existingTimesheet.setEmployeeId(updatedTimesheet.getEmployeeId());
        existingTimesheet.setFromDate(updatedTimesheet.getFromDate());
        existingTimesheet.setToDate(updatedTimesheet.getToDate());
        existingTimesheet.setProject(updatedTimesheet.getProject());
        existingTimesheet.setHoursWorked(updatedTimesheet.getHoursWorked());
        existingTimesheet.setDescription(updatedTimesheet.getDescription());

        return timesheetRepository.save(existingTimesheet);
    }

    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteById(id);
    }

    public Timesheet getTimesheetById(Long id) {
        return timesheetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Timesheet not found with id: " + id));
    }

    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    public Timesheet updateTimesheetStatus(Long id, String status) {
        Optional<Timesheet> optionalTimesheet = timesheetRepository.findById(id);
        if (optionalTimesheet.isEmpty()) {
            throw new NotFoundException("Timesheet not found with id: " + id);
        }

        Timesheet existingTimesheet = optionalTimesheet.get();
        existingTimesheet.setStatus(status);

        return timesheetRepository.save(existingTimesheet);
    }

}
