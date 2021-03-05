package com.tp.staffing.persistence.mappers;

import com.tp.staffing.exceptions.InvalidEmployeeIdException;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Week;

import java.util.List;

public interface WeekDAO {

    Week getWeekById(Integer id) throws InvalidEmployeeIdException;

    List<Employee> getEmployeesByLastName(String lastName);

    List<Employee> getEmployees();

    Integer addEmployee(Employee employee);

    boolean editEmployee(Integer id, Employee updatedEmployee);

    boolean deleteEmployee(Integer id);




}
