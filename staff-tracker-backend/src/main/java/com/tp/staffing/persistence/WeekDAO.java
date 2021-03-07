package com.tp.staffing.persistence;

import com.tp.staffing.exceptions.InvalidWeekIdException;
import com.tp.staffing.model.Week;
import com.tp.staffing.model.Week;

import java.time.LocalDate;
import java.util.List;

public interface WeekDAO {

    Week getWeekById(Integer id) throws InvalidWeekIdException;

    List<Week> getWeeksByStartDate(LocalDate startDate);

    List<Week> getWeeksByEndDate(LocalDate endDate);

    List<Week> getWeeksByContainsDate(LocalDate date);

    List<Week> getWeeks();

    Integer addWeek(Week week);

    boolean editWeek(Integer id, Week updatedWeek);

    boolean deleteWeek(Integer id);

}
