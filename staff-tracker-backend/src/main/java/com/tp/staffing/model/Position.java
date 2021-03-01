package com.tp.staffing.model;


import java.sql.Time;
import java.util.List;

public class Position {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private String title; //Cannot be null
    private Integer employeeId; //Foreign Key, can be null
    private String startTime; //Can be null
    private String endTime; //Can be null


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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
