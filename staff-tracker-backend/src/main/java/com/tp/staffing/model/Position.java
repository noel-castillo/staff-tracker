package com.tp.staffing.model;


import java.sql.Time;
import java.util.List;

public class Position {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private String title; //Cannot be null
    private Integer employeeId; //Foreign Key, can be null
    private int startTime; //Can be null
    private int endTime; //Can be null
    private Employee employee;
    private String shift;


    // C O N S T R U C T O R S
    public Position() {

    }

    public Position(Integer id, String title, Integer employeeId) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;

    }

    public Position(Position that) {
        this.id = that.id;
        this.title = that.title;
        this.employeeId = that.employeeId;
        this.startTime = that.startTime;
        this.endTime = that.endTime;

    }

    // M E T H O D S

    // G E T T E R S && S E T T E R S
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
