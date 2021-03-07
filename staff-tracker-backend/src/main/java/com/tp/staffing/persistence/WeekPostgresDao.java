package com.tp.staffing.persistence;

import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.WeekMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeekPostgresDao implements WeekDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a Week to the database and returns it's id.
    public Integer addWeek(Week week) {

        return template.query("INSERT INTO public.\"Week\"(\"startDate\", \"endDate\")" +
                "VALUES ( '" + week.getStartDate() + "', '" + week.getEndDate() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a Week from the database with the given id.
    // Or returns null if no Weeks are found with the given id.
    public Week getWeekById(Integer id) {
        List<Week> weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new WeekMapper());

        if (weeks.isEmpty()) {
            return null;
        }

        return weeks.get(0);
    }

    @Override //Returns a list of all Weeks in the database.
    public List<Week> getWeeks() {
        List<Week> weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n;", new WeekMapper());

        if (weeks.isEmpty()) {
            return null;
        }

        return weeks;
    }

    @Override //Returns a list of all Weeks in the database with the given date.
    public List<Week> getWeeksByStartDate(LocalDate date) {
        List<Week> weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n" +
                "\t\tWHERE \"startDate\" = '" + date + "';", new WeekMapper());

        if (weeks.isEmpty()) {
            return null;
        }

        return weeks;
    }

    @Override //Returns a list of all Weeks in the database with the given date.
    public List<Week> getWeeksByEndDate(LocalDate date) {
        List<Week> weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n" +
                "\t\tWHERE \"endDate\" = '" + date + "';", new WeekMapper());

        if (weeks.isEmpty()) {
            return null;
        }

        return weeks;
    }

    @Override //Returns a list of all Weeks in the database with the given date.
    public List<Week> getWeeksByContainsDate(LocalDate date) {
        List<Week> weeks = getWeeks();
        List<Week> weeksToReturn = new ArrayList<>();
        for(Week week : weeks){
            if(week.getStartDate().compareTo(date) <= 0 && week.getEndDate().compareTo(date) >= 0){
                weeksToReturn.add(week);
            }
        }

        if (weeksToReturn.isEmpty()) {
            return null;
        }

        return weeksToReturn;
    }

    @Override //Returns true or false if updating a Week was successful.
    public boolean editWeek(Integer id, Week updatedWeek) {
        if (getWeekById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Week\" " +
                    "SET \"startDate\"='" + updatedWeek.getStartDate() +
                    "', \"endDate\"='" + updatedWeek.getEndDate() + "' " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

    @Override //Returns true or false if deleting a Week was successful.
    public boolean deleteWeek(Integer id) {

        if (getWeekById(id) == null) {
            return false;
        } else {
            template.execute("DELETE FROM public.\"Week\" " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }


}
