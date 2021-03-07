package com.tp.staffing.controller;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Day;
import com.tp.staffing.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class DayController {
    @Autowired
    DayService service;

    //Adds a new day to the database by the given day. Date cannot be null.
    @PostMapping("/days/add-day")
    public Integer addDay(@RequestBody Day day) throws NullDayDateException {
        return service.addDay(day);
    }

    //Retrieves a day from the database by the given id.
    @GetMapping("/days/{id}")
    public Day getDayById(@PathVariable Integer id) throws InvalidDayIdException, NullDayIdException {
        return service.getDayById(id);
    }

    //Retrieves a list of Days by the given range from the database. Date ranges cannot be null.
    @GetMapping("/days/{startDate}/{endDate}")
    public List<Day> getDaysByRange(@PathVariable LocalDate startDate, LocalDate endDate) throws NullDayDateException {
        return service.getDaysByRange(startDate, endDate);
    }

    //Retrieves a list of all Days in the database.
    @GetMapping("/days")
    public List<Day> getDays() {
        return service.getDays();
    }

    //Edits an existing Day in the database by replacing it's attributes with the
    //attributes of the given Day. This is done on the Day with the given id.
    @PutMapping("/days/{id}")
    public String editDay(@PathVariable Integer id, @RequestBody Day day) throws NullDayDateException, InvalidDayIdException {
        if (service.editDay(id, day)) {
            return "Day " + id + " updated";
        } else {
            return "Day " + id + " not found";
        }

    }

    //Deletes an existing Day row from the database.
    @DeleteMapping("/days/delete/{id}")
    public String deleteDay(@PathVariable Integer id) throws InvalidDayIdException, NullDayIdException {
        if (service.deleteDay(id)) {
            return "Day " + id + " deleted";
        } else {
            return "Day " + id + " not found";
        }
    }

}
