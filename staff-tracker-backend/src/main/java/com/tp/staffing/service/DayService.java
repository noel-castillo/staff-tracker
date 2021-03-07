package com.tp.staffing.service;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Day;
import com.tp.staffing.persistence.DayPostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DayService {

    @Autowired
    DayPostgresDao dao;

    //Adds a Day to the database.
    //When attempting to add a Day to the database. An exception will be thrown if the given date
    //variable is null.
    public Integer addDay(Day Day) throws NullDayDateException {
        if (Day.getDate() == null) {
            throw new NullDayDateException("You cannot add a Day with a null date.");
        }

        return dao.addDay(Day);
    }

    //Retrieves a Day from the database by id.
    //When attempting to retrieve an id from the database. An exception will be thrown if given
    //a null for the id to look for.
    public Day getDayById(Integer id) throws NullDayIdException, InvalidDayIdException {
        if (id == null) {
            throw new NullDayIdException("You cannot retrieve a Day with null id.");
        }

        Day dayToReturn = dao.getDayById(id);
        if (dayToReturn == null) {
            throw new InvalidDayIdException("No Day with that ID exists.");
        } else {
            return dayToReturn;
        }

    }

    //Retrieves a list of Days by a given range. When getting a list of Days from the database by a given range, an exception will be
    //thrown if the given dates to check for are null.
    public List<Day> getDaysByRange(LocalDate startDate, LocalDate endDate) throws NullDayDateException {
        if (startDate == null || endDate == null) {
            throw new NullDayDateException("You cannot retrieve Days with a null date.");
        }

        return dao.getDaysByRange(startDate, endDate);
    }

    //Retrieves a list of all existing Days in the database.
    public List<Day> getDays() {
        return dao.getDays();
    }

    //Updates an existing Day in the database.
    //An exception will be thrown if attempting to update a Day to have a
    //null date.
    public boolean editDay(Integer id, Day day) throws NullDayDateException, InvalidDayIdException {
        if (day.getDate() == null) {
            throw new NullDayDateException("You cannot edit a Day to have a null date.");
        }
        return dao.editDay(id, day);
    }

    //Deletes ane existing Day from the database.
    //An exception will be thrown if given a null for the id to check for when deleting.
    public boolean deleteDay(Integer id) throws NullDayIdException, InvalidDayIdException {
        if (id == null) {
            throw new NullDayIdException("You cannot delete a Day with null id.");
        }
        return dao.deleteDay(id);
    }

}
