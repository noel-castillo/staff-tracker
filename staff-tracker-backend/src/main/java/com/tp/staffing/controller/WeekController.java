package com.tp.staffing.controller;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Week;
import com.tp.staffing.service.WeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class WeekController {
    @Autowired
    WeekService service;


    //Adds a new week to the database by the given Week. Dates cannot be null.
    @PostMapping("/weeks/add-week")
    public Integer addWeek(@RequestBody Week week) throws NullWeekStartDateException, NullWeekEndDateException {
        return service.addWeek(week);
    }

    //Retrieves a week from the database by the given id.
    @GetMapping("/weeks/{id}")
    public Week getWeekById(@PathVariable Integer id) {
        return service.getWeekById(id);
    }

    //Retrieves a list of weeks by the given start date from the database. Date cannot be null.
    @GetMapping("/weeks/start/{startDate}")
    public List<Week> getWeeksByStartDate(@PathVariable LocalDate startDate) {
        return service.getWeeksByStartDate(startDate);
    }

    //Retrieves a list of weeks by the given end date from the database. Date cannot be null.
    @GetMapping("/weeks/end/{endDate}")
    public List<Week> getWeeksByEndDate(@PathVariable LocalDate endDate) {
        return service.getWeeksByEndDate(endDate);
    }

    //Retrieves a list of weeks by the given end date from the database. Date cannot be null.
    @GetMapping("/weeks/contains/{date}")
    public List<Week> getWeeksByDate(@PathVariable LocalDate date) {
        return service.getWeeksByDate(date);
    }

    //Retrieves a list of all Weeks in the database.
    @GetMapping("/weeks")
    public List<Week> getWeeks() {
        return service.getWeeks();
    }

    //Edits an existing Week in the database by replacing it's attributes with the
    //attributes of the given Week. This is done on the Week with the given id.
    @PutMapping("/weeks/{id}")
    public String editWeek(@PathVariable Integer id, @RequestBody Week week) {
        if (service.editWeek(id, week)) {
            return "Week " + id + " updated";
        } else {
            return "Week " + id + " not found";
        }

    }

    //Deletes an existing week row from the database.
    @DeleteMapping("/weeks/delete/{id}")
    public String deleteWeek(@PathVariable Integer id) {
        if (service.deleteWeek(id)) {
            return "Week " + id + " deleted";
        } else {
            return "Week " + id + " not found";
        }
    }
}
