package com.tp.staffing.service;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.WeekPostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeekService {

    @Autowired
    WeekPostgresDao dao;

    //Adds a Week to the database.
    //When attempting to add a Week to the database. An exception will be thrown if the given dates
    //variable is null.
    public Integer addWeek(Week week) throws NullWeekStartDateException, NullWeekEndDateException {
        if (week.getStartDate() == null) {
            throw new NullWeekStartDateException("You cannot add a Week with a null start date.");
        }

        if (week.getEndDate() == null) {
            throw new NullWeekEndDateException("You cannot add a Week with a null end date.");
        }

        return dao.addWeek(week);
    }

    //Retrieves a Week from the database by id.
    //When attempting to retrieve an id from the database. An exception will be thrown if given
    //a null for the id to look for.
    public Week getWeekById(Integer id) throws InvalidWeekIdException {

        Week WeekToReturn = dao.getWeekById(id);
        if (WeekToReturn == null) {
            throw new InvalidWeekIdException("No Week with that ID exists.");
        } else {
            return WeekToReturn;
        }

    }

    //Retrieves a list of Weeks by a given start date.
    //Exception thrown if the given date to check for is null.
    public List<Week> getWeeksByStartDate(LocalDate startDate) throws NullWeekStartDateException {
        if (startDate == null) {
            throw new NullWeekStartDateException("You cannot retrieve Weeks with a null start date.");
        }

        return dao.getWeeksByStartDate(startDate);
    }

    //Retrieves a list of Weeks by a given end date.
    //Exception thrown if the given date to check for is null.
    public List<Week> getWeeksByEndDate(LocalDate endDate) throws NullWeekEndDateException {
        if (endDate == null) {
            throw new NullWeekEndDateException("You cannot retrieve Weeks with a null end date.");
        }

        return dao.getWeeksByEndDate(endDate);
    }

    //Retrieves a list of Weeks that contains a given  date.
    //Exception thrown if the given date to check for is null.
    public List<Week> getWeeksByContainsDate(LocalDate date) throws NullDateException {
        if (date == null) {
            throw new NullDateException("You cannot retrieve Weeks with a null date.");
        }

        return dao.getWeeksByContainsDate(date);
    }

    //Retrieves a list of all existing Weeks in the database.
    public List<Week> getWeeks() {
        return dao.getWeeks();
    }

    //Updates an existing Week in the database.
    //An exception will be thrown if attempting to update a Week to have a
    //null date.
    public boolean editWeek(Integer id, Week week) throws InvalidWeekIdException, NullWeekStartDateException, NullWeekEndDateException {
        if (week.getStartDate() == null) {
            throw new NullWeekStartDateException("You cannot add a Week with a null start date.");
        }

        if (week.getEndDate() == null) {
            throw new NullWeekEndDateException("You cannot add a Week with a null end date.");
        }
        return dao.editWeek(id, week);
    }

    //Deletes ane existing Week from the database.
    //An exception will be thrown if given a null for the id to check for when deleting.
    public boolean deleteWeek(Integer id) throws NullWeekIdException, InvalidWeekIdException {
        if (id == null) {
            throw new NullWeekIdException("You cannot delete a Week with null id.");
        }
        return dao.deleteWeek(id);
    }

}
