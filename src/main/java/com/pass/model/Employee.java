package com.pass.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
	
	



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accessCardId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "shift")
//    private Shift shift;
	

    
    public Employee() {
    }

    public Employee(String name, String location) {
        this.name = name;
        this.location = location;
        //this.shift = shift;
    }

    // Getters and Setters
    public Long getAccessCardId() {
        return accessCardId;
    }

    public void setAccessCardId(Long accessCardId) {
        this.accessCardId = accessCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    

//    public Shift getShift() {
//        return shift;
//    }
//
//    public void setShift(Shift shift) {
//        this.shift = shift;
//    }

    // Optional: toString() method for printing the object (useful for debugging)
    @Override
    public String toString() {
        return "Employee{" +
                "accessCardId=" + accessCardId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

	
}