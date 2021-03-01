package com.tp.staffing.persistence;

import com.tp.staffing.model.Employee;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EmployeePostgresDao implements EmployeeDAO {


    @Autowired
    private JdbcTemplate template;

    @Override //Adds an employee to the database and returns it's id.
    public Integer addEmployee(Employee employee) {

        return template.query("INSERT INTO public.\"Employee\"(\"firstName\", \"lastName\")" +
                "VALUES ( '" + employee.getFirstName() + "', '" + employee.getLastName() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);

    }

    @Override //Returns an employee from the database. Returns null if not found by given id.
    public Employee getEmployeeById(Integer id) {
        List<Employee> employees = template.query("SELECT id, \"firstName\", \"lastName\"\n" +
                "\tFROM public.\"Employee\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new EmployeeMapper());

        if (employees.isEmpty()) {
            return null;
        }

        return employees.get(0);
    }

    @Override //Returns a list of employees with the given last name to check for.
    public List<Employee> getEmployeesByLastName(String lastName) {
        List<Employee> employees = template.query("SELECT id, \"firstName\", \"lastName\"\n" +
                "\tFROM public.\"Employee\"\n" +
                "\t\tWHERE \"lastName\" = '" + lastName + "';", new EmployeeMapper());

        if (employees.isEmpty()) {
            return null;
        }

        return employees;
    }

    @Override //Returns a list of all employees in the database.
    public List<Employee> getEmployees() {
        List<Employee> employees = template.query("SELECT id, \"firstName\", \"lastName\"\n" +
                "\tFROM public.\"Employee\"\n;", new EmployeeMapper());

        if (employees.isEmpty()) {
            return null;
        }

        return employees;
    }

    @Override //Returns true or false if updating an employee in the database was successful.
    public boolean editEmployee(Integer id, Employee updatedEmployee) {
        if (getEmployeeById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Employee\"\n" +
                    "SET \"firstName\"='" + updatedEmployee.getFirstName() + "', " +
                    "\"lastName\" ='" + updatedEmployee.getLastName() + "'\n" +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

    @Override //Returns true or false if deleting an employee from the database was successful.
    public boolean deleteEmployee(Integer id) {

        if (getEmployeeById(id) == null) {
            return false;
        } else {
            template.execute("DELETE FROM public.\"Employee\" " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

}
