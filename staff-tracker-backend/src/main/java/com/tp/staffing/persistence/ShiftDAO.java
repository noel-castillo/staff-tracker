package com.tp.staffing.persistence;

import com.tp.staffing.exceptions.InvalidShiftIdException;
import com.tp.staffing.model.Shift;

import java.time.LocalDate;
import java.util.List;

public interface ShiftDAO {

    Shift getShiftById(Integer id);

    List<Shift> getShifts();

    Integer addShift(Shift shift);

    boolean deleteShift(Integer id);

    boolean editShift(Integer id, Shift updatedShift);


}
