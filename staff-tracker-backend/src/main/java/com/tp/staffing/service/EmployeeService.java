package com.tp.staffing.service;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.persistence.EmployeePostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeePostgresDao dao;

    //Adds an employee to the database.
    //When adding an employee, an exception will be thrown if either first name or last name variables
    //are null or empty strings.
    public Integer addEmployee(Employee employee) throws NullEmployeeFirstNameException, NullEmployeeLastNameException, InvalidEmployeeFirstNameException, InvalidEmployeeLastNameException {

        if (employee.getFirstName() == null) {
            throw new NullEmployeeFirstNameException("You cannot add a Employee with a null first name.");
        }
        if (employee.getLastName() == null) {
            throw new NullEmployeeLastNameException("You cannot add a Employee with a null last name.");
        }
        if (employee.getFirstName().trim().equals("")) {
            throw new InvalidEmployeeFirstNameException("You cannot add a Employee with a empty first name.");
        }
        if (employee.getLastName().trim().equals("")) {
            throw new InvalidEmployeeLastNameException("You cannot add a Employee with a empty last name.");
        }
        return dao.addEmployee(employee);
    }

    //Retrieves an employee from the database by it's id.
    //When retrieving an employee from the database. An exception will be thrown if given a null for the id.
    public Employee getEmployeeById(Integer id) throws NullEmployeeIdException, InvalidEmployeeIdException {
        if (id == null) {
            throw new NullEmployeeIdException("You cannot retrieve an Employee with null id.");
        }

        Employee employeeToReturn = dao.getEmployeeById(id);
        if (employeeToReturn == null) {
            throw new InvalidEmployeeIdException("No Employee with that ID exists.");
        } else {
            return employeeToReturn;
        }
    }

    //Retrieves a list of employees from the database by a given last name to check for.
    //When retrieving a list of employees by last name. An exception will be thrown if
    //the given last name to check for is either null or an empty string.
    public List<Employee> getEmployeesByLastName(String lastName) throws NullEmployeeLastNameException, InvalidEmployeeLastNameException {
        if (lastName == null) {
            throw new NullEmployeeLastNameException("You cannot retrieve Employees with null last name.");
        }

        if (lastName.trim().equals("")) {
            throw new InvalidEmployeeLastNameException("You cannot retrieve Employees with a empty last name.");
        }
        return dao.getEmployeesByLastName(lastName);
    }

    //Retrieves a list of all employees in the database.
    public List<Employee> getEmployees() {
        return dao.getEmployees();
    }

    //Updates an existing employee from the database.
    //When editing an existing employee. An exception will be thrown if attempted to update first or last name
    //variables with a null or empty string.
    public boolean editEmployee(Integer id, Employee employee) throws NullEmployeeFirstNameException, NullEmployeeLastNameException, InvalidEmployeeFirstNameException, InvalidEmployeeLastNameException {
        if (employee.getFirstName() == null) {
            throw new NullEmployeeFirstNameException("You cannot edit a Employee to have a null first name.");
        }
        if (employee.getLastName() == null) {
            throw new NullEmployeeLastNameException("You cannot edit a Employee to have a null last name.");
        }
        if (employee.getFirstName().trim().equals("")) {
            throw new InvalidEmployeeFirstNameException("You cannot edit a Employee to have a empty first name.");
        }
        if (employee.getLastName().trim().equals("")) {
            throw new InvalidEmployeeLastNameException("You cannot edit a Employee to have a empty last name.");
        }
        return dao.editEmployee(id, employee);
    }

    //Deletes an existing employee from the database.
    //When deleting an employee, an exception will be thrown if provided with a null for the id.
    public boolean deleteEmployee(Integer id) throws NullEmployeeIdException {
        if (id == null) {
            throw new NullEmployeeIdException("You cannot delete a Employee with null id.");
        }
        return dao.deleteEmployee(id);
    }


}
