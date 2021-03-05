package com.tp.staffing.model;


import java.time.LocalDate;
import java.util.Date;

public class Week {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private LocalDate startDate; //Cannot be null
    private LocalDate endDate; //Foreign Key, can be null


    // C O N S T R U C T O R S
    public Week() {

    }

    public Week(Integer id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public Week(Week that) {
        this.id = that.id;
        this.startDate = that.startDate;
        this.endDate = that.endDate;

    }

    // M E T H O D S

    // G E T T E R S && S E T T E R S
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
