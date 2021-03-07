package com.tp.staffing.service;


import com.tp.staffing.exceptions.InvalidShiftIdException;
import com.tp.staffing.exceptions.NullShiftIdException;
import com.tp.staffing.exceptions.NullShiftNameException;
import com.tp.staffing.exceptions.NullShiftTimeException;
import com.tp.staffing.model.Shift;
import com.tp.staffing.persistence.ShiftPostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {

    @Autowired
    ShiftPostgresDao dao;

    //Adds a Shift to the database.
    //When attempting to add a Shift to the database. An exception will be thrown if the given name,
    //startTime, or endTime variables are null.
    public Integer addShift(Shift shift) throws NullShiftNameException, NullShiftTimeException {
        if (shift.getName() == null) {
            throw new NullShiftNameException("You cannot add a Shift with a null name.");
        }

        return dao.addShift(shift);
    }

    //Retrieves a Shift from the database by id.
    //When attempting to retrieve an id from the database. An exception will be thrown if given
    //a null or invalid value for the id to look for.
    public Shift getShiftById(Integer id) throws NullShiftIdException, InvalidShiftIdException {
        if (id == null) {
            throw new NullShiftIdException("You cannot retrieve a Shift with null id.");
        }

        Shift shiftToReturn = dao.getShiftById(id);
        if (shiftToReturn == null) {
            throw new InvalidShiftIdException("No Shift with that ID exists.");
        } else {
            return shiftToReturn;
        }

    }

    //Retrieves a list of all existing Shifts in the database.
    public List<Shift> getShifts() {
        return dao.getShifts();
    }

    //Updates an existing Shift in the database.
    //An exception will be thrown if attempting to update a shift to have a
    //null name, startTime, or endTime.
    public boolean editShift(Integer id, Shift shift) throws NullShiftNameException, NullShiftTimeException {
        if (shift.getName() == null) {
            throw new NullShiftNameException("You cannot add a Shift with a null name.");
        }

        return dao.editShift(id, shift);
    }

    //Deletes an existing shift from the database.
    //An exception will be thrown if given a null for the id to check for when deleting.
    public boolean deleteShift(Integer id) throws NullShiftIdException, InvalidShiftIdException {
        if (id == null) {
            throw new NullShiftIdException("You cannot delete a Shift with null id.");
        }
        return dao.deleteShift(id);
    }

}
