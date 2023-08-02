package com.pass.dto;

import java.time.LocalDate;

public interface AttendanceReport {

    Long getId();
    String getName();
    String getLocation();
    Double getHoursWorked();
    LocalDate getDate();
    
}
