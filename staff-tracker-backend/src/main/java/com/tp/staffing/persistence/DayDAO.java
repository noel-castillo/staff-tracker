package com.tp.staffing.persistence;

import com.tp.staffing.exceptions.InvalidDayIdException;
import com.tp.staffing.model.Day;

import java.time.LocalDate;
import java.util.List;

public interface DayDAO {

    Day getDayById(Integer id) throws InvalidDayIdException;

    List<Day> getDaysByRange(LocalDate startDate, LocalDate endDate);

    List<Day> getDays();

    Integer addDay(Day Day);

    boolean deleteDay(Integer id) throws InvalidDayIdException;

    boolean editDay(Integer id, Day updatedDay) throws InvalidDayIdException;



}
