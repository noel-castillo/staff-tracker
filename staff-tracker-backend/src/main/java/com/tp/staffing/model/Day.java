package com.tp.staffing.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Day {

    // V A R I A B L E S
    private Integer id; //Primary Key, Cannot be null
    private LocalDate date; //Cannot be null
    private Integer weekId;
    private List<Position> positions;


    // C O N S T R U C T O R S
    public Day() {

    }

    public Day(Integer id, LocalDate date, Integer weekId) {
        this.id = id;
        this.date = date;
        this.weekId = weekId;

    }

    public Day(Day that) {
        this.id = that.id;
        this.date = that.date;
        this.weekId = that.weekId;

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

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
