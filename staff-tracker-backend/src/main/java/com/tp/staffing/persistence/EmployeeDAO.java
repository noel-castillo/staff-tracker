package com.tp.staffing.persistence;

import com.tp.staffing.exceptions.InvalidEmployeeIdException;
import com.tp.staffing.model.Employee;

import java.awt.print.Book;
import java.util.List;

public interface EmployeeDAO {

    Employee getEmployeeById(Integer id) throws InvalidEmployeeIdException;

    List<Employee> getEmployeesByLastName(String lastName);

    List<Employee> getEmployees();

    Integer addEmployee(Employee employee);

    boolean editEmployee(Integer id, Employee updatedEmployee);

    boolean deleteEmployee(Integer id);




}
