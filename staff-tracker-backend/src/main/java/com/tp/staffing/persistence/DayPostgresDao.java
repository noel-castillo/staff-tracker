package com.tp.staffing.persistence;

import com.tp.staffing.model.Day;
import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.DayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DayPostgresDao implements DayDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a Day to the database and returns it's id.
    public Integer addDay(Day day) {

        return template.query("INSERT INTO public.\"Day\"(\"date\")" +
                "VALUES ( '" + day.getDate() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a Day from the database with the given id.
    // Or returns null if no Days are found with the given id.
    public Day getDayById(Integer id) {
        List<Day> days = template.query("SELECT * " +
                "\tFROM public.\"Day\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new DayMapper());

        if (days.isEmpty()) {
            return null;
        }

        return days.get(0);
    }

    @Override //Returns a list of all Days in the database.
    public List<Day> getDays() {
        List<Day> days = template.query("SELECT * " +
                "\tFROM public.\"Day\"\n;", new DayMapper());

        if (days.isEmpty()) {
            return null;
        }

        return days;
    }

    @Override //Returns a list of all Days in the database within the given date range.
    public List<Day> getDaysByRange(LocalDate startDate, LocalDate endDate) {
        List<Day> days = getDays();
        List<Day> daysToReturn = new ArrayList<>();
        for(Day day : days){
            if(day.getDate().compareTo(startDate) >= 0 && day.getDate().compareTo(endDate) <= 0){
                daysToReturn.add(day);
            }
        }

        if (daysToReturn.isEmpty()) {
            return null;
        }

        return daysToReturn;
    }

    @Override //Returns true or false if updating a Day was successful.
    public boolean editDay(Integer id, Day updatedDay) {
        if (getDayById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Day\" " +
                    "SET \"date\"='" + updatedDay.getDate() + "' " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

    @Override //Returns true or false if deleting a Day was successful.
    public boolean deleteDay(Integer id) {

        if (getDayById(id) == null) {
            return false;
        } else {
            template.execute("DELETE FROM public.\"Day\" " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }


}
