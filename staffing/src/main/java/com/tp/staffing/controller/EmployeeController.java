package com.tp.staffing.controller;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    //Retrieves an employee by the given id from the database.
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) throws NullEmployeeIdException, InvalidEmployeeIdException {
        return service.getEmployeeById(id);
    }

    //Retrieves a list of employees by the given last name from the database.
    @GetMapping("/employees/{lastName}")
    public List<Employee> getEmployeesByLastName(@PathVariable String lastName) throws NullEmployeeLastNameException, InvalidEmployeeLastNameException {
        return service.getEmployeesByLastName(lastName);
    }

    //Retrieves a list of all employees from the database.
    @GetMapping("/employees/")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }

    //Adds an employee to the database with a given employee body. First name and Last name
    // cannot be null or empty.
    @PostMapping("/employees/add-employee")
    public Integer addEmployee(@RequestBody Employee employee) throws InvalidEmployeeFirstNameException, NullEmployeeLastNameException, NullEmployeeFirstNameException, InvalidEmployeeLastNameException {
        return service.addEmployee(employee);
    }

    //Edits an existing employee in the database by replacing its attributes
    // with the attributes of the given employee. First and Last name cannot be null or empty.
    @PutMapping("/employees/{id}")
    public String editEmployee(@PathVariable Integer id, @RequestBody Employee employee) throws InvalidEmployeeFirstNameException, NullEmployeeLastNameException, NullEmployeeFirstNameException, InvalidEmployeeLastNameException {
        if (service.editEmployee(id, employee)) {
            return "Employee " + id + " updated";
        } else {
            return "Employee " + id + " not found";
        }
    }

    //Deletes an employee by the given id in the database.
    @DeleteMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) throws NullEmployeeIdException {
        if (service.deleteEmployee(id)) {
            return "Employee " + id + " deleted";
        } else {
            return "Employee " + id + " not found";
        }
    }



}
