package com.tp.staffing.persistence;

import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.WeekMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeekPostgresDao implements WeekDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a Week to the database and returns it's id.
    public Integer addWeek(Week week) {

        return template.query("INSERT INTO public.\"Week\"(\"startDate\", \"endDate\")" +
                "VALUES ( '" + week.getStartDate() + "', '"  + week.getEndDate() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a Week from the database with the given id.
    // Or returns null if no Weeks are found with the given id.
    public Week getWeekById(Integer id) {
        List<Week> Weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new WeekMapper());

        if (Weeks.isEmpty()) {
            return null;
        }

        return Weeks.get(0);
    }

    @Override //Returns a list of all Weeks in the database.
    public List<Week> getWeeks() {
        List<Week> Weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n;", new WeekMapper());

        if (Weeks.isEmpty()) {
            return null;
        }

        return Weeks;
    }

    @Override //Returns a list of all Weeks in the database with the given title.
    public List<Week> getWeeksByTitle(String title) {
        List<Week> Weeks = template.query("SELECT * " +
                "\tFROM public.\"Week\"\n" +
                "\t\tWHERE \"title\" = '" + title + "';", new WeekMapper());

        if (Weeks.isEmpty()) {
            return null;
        }

        return Weeks;
    }

    @Override //Returns true or false if updating a Week was successful.
    public boolean editWeek(Integer id, Week updatedWeek) {
        if (getWeekById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Week\" " +
                    "SET \"title\"='" + updatedWeek.getTitle() +
                    "', \"startTime\"='" + updatedWeek.getStartTime() +
                    "', \"endTime\"='" + updatedWeek.getEndTime() + "' " +
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

    @Override //Adds an employee id to a Week entity. Returns true or false if successful.
    public boolean addEmployeeToWeek(Integer employeeId, Integer WeekId) {

        List<Employee> employees = template.query("SELECT id, \"firstName\", \"lastName\"\n" +
                "\tFROM public.\"Employee\"\n" +
                "\t\tWHERE \"id\" = '" + employeeId + "';", new EmployeeMapper());
        if (employees.isEmpty()) {
            return false;
        }

        if (getWeekById(WeekId) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Week\"\n" +
                    "SET \"employeeId\"='" + employeeId + "'\n" +
                    "WHERE \"id\" = " + WeekId + ";");
            return true;
        }

    }

    @Override //Removes an employee id from a Week. Returns true or false if successful.
    public boolean removeEmployeeFromWeek(Integer WeekId) {

        if (getWeekById(WeekId) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Week\"\n" +
                    "SET \"employeeId\"=null\n" +
                    "WHERE \"id\" = " + WeekId + ";");
            return true;
        }

    }

    //Need methods to implement adding days to a Week. There is a WeekDay bridge table.
    //Pre
}
