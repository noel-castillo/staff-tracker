package com.tp.staffing.persistence;

import com.tp.staffing.model.Day;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.DayMapper;
import com.tp.staffing.persistence.mappers.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class DayPostgresDao implements DayDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a Day to the database and returns it's id.
    public Integer addDay(Day day) {

        return template.query("INSERT INTO public.\"Day\"(\"date\", \"weekId\")" +
                "VALUES ( '" + day.getDate() + "', '" + day.getWeekId() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a Day from the database with the given id.
    // Or returns null if no Days are found with the given id.
    //Attaches all positions to the day.
    public Day getDayById(Integer id) {
        List<Day> days = template.query("SELECT * " +
                "\tFROM public.\"Day\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new DayMapper());

        if (days.isEmpty()) {
            return null;
        }

        List<Position> positions = template.query("SELECT p.* \n" +
                "FROM \"Position\" as p\n" +
                "RIGHT JOIN \"PositionDay\" as pd \n" +
                "ON p.\"id\" = pd.\"positionId\"\n" +
                "RIGHT JOIN \"Day\" as d\n" +
                "ON pd.\"dayId\" = d.\"id\"\n" +
                "WHERE d.\"id\" = '" + days.get(0).getId() + "';", new PositionMapper());

        for (Position position : positions) {
            if (position.getEmployeeId() != 0) {
                Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                        "\"email\", \"phone\", \"address\", \"enabled\"" +
                        "\tFROM public.\"Employee\"\n" +
                        "\t\tWHERE \"id\" = '" + position.getEmployeeId() + "';", new EmployeeMapper()).get(0);
                position.setEmployee(employee);
            }
        }
        Collections.sort(positions, new SortByTitle());
        Collections.sort(positions, new SortByShift());
        days.get(0).setPositions(positions);

        return days.get(0);
    }

    @Override //Returns a list of all Days in the database. Attaches all positions on that day.
    public List<Day> getDays() {
        List<Day> days = template.query("SELECT * " +
                "\tFROM public.\"Day\"\n;", new DayMapper());

        if (days.isEmpty()) {
            return null;
        }

        for (Day day : days) {
            List<Position> positions = template.query("SELECT p.* \n" +
                    "FROM \"Position\" as p\n" +
                    "RIGHT JOIN \"PositionDay\" as pd \n" +
                    "ON p.\"id\" = pd.\"positionId\"\n" +
                    "RIGHT JOIN \"Day\" as d\n" +
                    "ON pd.\"dayId\" = d.\"id\"\n" +
                    "WHERE d.\"id\" = '" + day.getId() + "';", new PositionMapper());

            for (Position position : positions) {
                if (position.getEmployeeId() != 0) {
                    Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                            "\"email\", \"phone\", \"address\", \"enabled\"" +
                            "\tFROM public.\"Employee\"\n" +
                            "\t\tWHERE \"id\" = '" + position.getEmployeeId() + "';", new EmployeeMapper()).get(0);
                    position.setEmployee(employee);
                }
            }
            Collections.sort(positions, new SortByTitle());
            Collections.sort(positions, new SortByShift());
            day.setPositions(positions);
        }

        return days;
    }

    @Override //Returns a list of all Days in the database within the given date range.
    //Attaches all positions to the days.
    public List<Day> getDaysByRange(LocalDate startDate, LocalDate endDate) {
        List<Day> days = getDays();
        List<Day> daysToReturn = new ArrayList<>();
        for (Day day : days) {
            if (day.getDate().compareTo(startDate) >= 0 && day.getDate().compareTo(endDate) <= 0) {
                daysToReturn.add(day);
            }
        }

        if (daysToReturn.isEmpty()) {
            return null;
        }

        for (Day day : days) {
            List<Position> positions = template.query("SELECT p.* \n" +
                    "FROM \"Position\" as p\n" +
                    "RIGHT JOIN \"PositionDay\" as pd \n" +
                    "ON p.\"id\" = pd.\"positionId\"\n" +
                    "RIGHT JOIN \"Day\" as d\n" +
                    "ON pd.\"dayId\" = d.\"id\"\n" +
                    "WHERE d.\"id\" = '" + day.getId() + "';", new PositionMapper());
            for (Position position : positions) {
                if (position.getEmployeeId() != 0) {
                    Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                            "\"email\", \"phone\", \"address\", \"enabled\"" +
                            "\tFROM public.\"Employee\"\n" +
                            "\t\tWHERE \"id\" = '" + position.getEmployeeId() + "';", new EmployeeMapper()).get(0);
                    position.setEmployee(employee);
                }
            }
            Collections.sort(positions, new SortByTitle());
            Collections.sort(positions, new SortByShift());
            day.setPositions(positions);
        }

        return daysToReturn;
    }

    @Override //Returns true or false if updating a Day was successful.
    public boolean editDay(Integer id, Day updatedDay) {
        if (getDayById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Day\" " +
                    "SET \"date\"='" + updatedDay.getDate() + "', " +
                    "\"weekId\"='" + updatedDay.getWeekId() + "'" +
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

    static class SortByTitle implements Comparator<Position> {
        @Override
        public int compare(Position a, Position b) {
            return a.getTitle().compareTo(b.getTitle());
        }
    }

    static class SortByShift implements Comparator<Position> {
        @Override
        public int compare(Position a, Position b) {
            return b.getShift().compareTo(a.getShift());
        }
    }


}
