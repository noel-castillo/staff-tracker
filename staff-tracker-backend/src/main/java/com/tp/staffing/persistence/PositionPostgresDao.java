package com.tp.staffing.persistence;

import com.tp.staffing.exceptions.InvalidPositionIdException;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PositionPostgresDao implements PositionDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a position to the database and returns it's id.
    public Integer addPosition(Position position) {

        return template.query("INSERT INTO public.\"Position\"(\"title\", \"employeeId\", \"startTime\", \"endTime\")" +
                "VALUES ( '" + position.getTitle() + "', '" + position.getEmployeeId() + "', '" + position.getStartTime() + "', '" + position.getEndTime() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a position from the database with the given id.
    // Or returns null if no positions are found with the given id.
    public Position getPositionById(Integer id) {
        List<Position> positions = template.query("SELECT * " +
                "\tFROM public.\"Position\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new PositionMapper());

        if (positions.isEmpty()) {
            return null;
        }

        if (positions.get(0).getEmployeeId() != 0) {
            Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                    "\"email\", \"phone\", \"address\", \"enabled\"" +
                    "\tFROM public.\"Employee\"\n" +
                    "\t\tWHERE \"id\" = '" + positions.get(0).getEmployeeId() + "';", new EmployeeMapper()).get(0);
            positions.get(0).setEmployee(employee);
        }


        return positions.get(0);
    }

    @Override //Returns a list of all positions in the database.
    public List<Position> getPositions() {
        List<Position> positions = template.query("SELECT * " +
                "\tFROM public.\"Position\"\n;", new PositionMapper());

        if (positions.isEmpty()) {
            return null;
        }

        for (Position position : positions) {
            if (position.getEmployeeId() != 0) {
                Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                        "\"email\", \"phone\", \"address\", \"enabled\"" +
                        "\tFROM public.\"Employee\"\n" +
                        "\t\tWHERE \"id\" = '" + position.getEmployeeId() + "';", new EmployeeMapper()).get(0);
                position.setEmployee(employee);
            }
        }

        return positions;
    }

    @Override //Returns a list of all positions in the database with the given title.
    public List<Position> getPositionsByTitle(String title) {
        List<Position> positions = template.query("SELECT * " +
                "\tFROM public.\"Position\"\n" +
                "\t\tWHERE \"title\" = '" + title + "';", new PositionMapper());

        if (positions.isEmpty()) {
            return null;
        }

        for (Position position : positions) {
            if (position.getEmployeeId() != 0) {
                Employee employee = template.query("SELECT id, \"firstName\", \"lastName\", " +
                        "\"email\", \"phone\", \"address\", \"enabled\"" +
                        "\tFROM public.\"Employee\"\n" +
                        "\t\tWHERE \"id\" = '" + position.getEmployeeId() + "';", new EmployeeMapper()).get(0);
                position.setEmployee(employee);
            }
        }
        return positions;
    }

    @Override //Returns true or false if updating a position was successful.
    public boolean editPosition(Integer id, Position updatedPosition) {
        if (getPositionById(id) == null) {
            return false;
        } else {
            if (updatedPosition.getEmployeeId() == 0) {
                template.execute("UPDATE public.\"Position\" " +
                        "SET \"title\"='" + updatedPosition.getTitle() +
                        "', \"employeeId\"=null" +
                        ", \"startTime\"='" + updatedPosition.getStartTime() +
                        "', \"endTime\"='" + updatedPosition.getEndTime() + "' " +
                        "WHERE \"id\" = " + id + ";");
                return true;
            } else {
                template.execute("UPDATE public.\"Position\" " +
                        "SET \"title\"='" + updatedPosition.getTitle() +
                        "', \"employeeId\"='" + updatedPosition.getEmployeeId() +
                        "', \"startTime\"='" + updatedPosition.getStartTime() +
                        "', \"endTime\"='" + updatedPosition.getEndTime() + "' " +
                        "WHERE \"id\" = " + id + ";");
                return true;
            }
        }
    }

    @Override //Returns true or false if deleting a position was successful.
    public boolean deletePosition(Integer id) {

        if (getPositionById(id) == null) {
            return false;
        } else {
            template.execute("DELETE FROM public.\"Position\" " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

    @Override //Adds an employee id to a position entity. Returns true or false if successful.
    public boolean addEmployeeToPosition(Integer employeeId, Integer positionId) {

        List<Employee> employees = template.query("SELECT id, \"firstName\", \"lastName\", " +
                "\"email\", \"phone\", \"address\", \"enabled\"" +
                "\tFROM public.\"Employee\"\n" +
                "\t\tWHERE \"id\" = '" + employeeId + "';", new EmployeeMapper());
        if (employees.isEmpty()) {
            return false;
        }

        if (getPositionById(positionId) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Position\"\n" +
                    "SET \"employeeId\"='" + employeeId + "'\n" +
                    "WHERE \"id\" = " + positionId + ";");
            return true;
        }

    }

    @Override //Removes an employee id from a position. Returns true or false if successful.
    public boolean removeEmployeeFromPosition(Integer positionId) {

        if (getPositionById(positionId) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Position\"\n" +
                    "SET \"employeeId\"=null\n" +
                    "WHERE \"id\" = " + positionId + ";");
            return true;
        }

    }

}
