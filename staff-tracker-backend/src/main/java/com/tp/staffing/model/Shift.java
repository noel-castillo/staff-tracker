package com.tp.staffing.model;


import java.util.Date;

public class Shift {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private String name; //Cannot be null
    private int startTime; //Cannot be null
    private int endTime; //Cannot be null


    // C O N S T R U C T O R S
    public Shift() {

    }

    public Shift(Integer id, String name, int startTime, int endTime) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    public Shift(Shift that) {
        this.id = that.id;
        this.name = that.name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
