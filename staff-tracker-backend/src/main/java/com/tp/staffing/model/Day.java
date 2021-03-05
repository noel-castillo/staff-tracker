package com.tp.staffing.model;


import java.time.LocalDate;
import java.util.Date;

public class Day {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private LocalDate date; //Cannot be null


    // C O N S T R U C T O R S
    public Day() {

    }

    public Day(Integer id, LocalDate date) {
        this.id = id;
        this.date = date;

    }

    public Day(Day that) {
        this.id = that.id;
        this.date = that.date;

    }

    // M E T H O D S

    // G E T T E R S && S E T T E R S
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
