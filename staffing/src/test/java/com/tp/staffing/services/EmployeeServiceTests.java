package com.tp.staffing.services;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    EmployeeService toTest;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {
        //None of these tests should reach into the database as exceptions should be thrown
        //prior to that. Nothing to setup for.
    }

    @Test //Testing method properly handles adding an employee to the database when given a null first name.
    public void addEmployeeNullFirstNameTest() {
        try {
            Employee employeeToAdd = new Employee();
            employeeToAdd.setFirstName(null);
            employeeToAdd.setLastName("Schmo");
            toTest.addEmployee(employeeToAdd);

        } catch (NullEmployeeFirstNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles adding an employee to the database when given null last name.
    public void addEmployeeNullLastNameTest() {
        try {
            Employee employeeToAdd = new Employee();
            employeeToAdd.setFirstName("Joe");
            employeeToAdd.setLastName(null);
            toTest.addEmployee(employeeToAdd);

        } catch (NullEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles adding an employee to the database when given an empty first name.
    public void addEmployeeEmptyFirstNameTest() {
        try {
            Employee employeeToAdd = new Employee();
            employeeToAdd.setFirstName("");
            employeeToAdd.setLastName("Schmo");
            toTest.addEmployee(employeeToAdd);

        } catch (InvalidEmployeeFirstNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles adding an employee to the database when given an empty last name.
    public void addEmployeeEmptyLastNameTest() {
        try {
            Employee employeeToAdd = new Employee();
            employeeToAdd.setFirstName("Joe");
            employeeToAdd.setLastName("");
            toTest.addEmployee(employeeToAdd);

        } catch (InvalidEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving an employee from the database by id
    // when given a null id.
    public void getEmployeeByIdNullIdTest() {
        try {
            toTest.getEmployeeById(null);

        } catch (NullEmployeeIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving an employee from the database
    // when given an invalid id.
    public void getEmployeeByIdInvalidUpperBoundIdTest() {
        try {
            toTest.getEmployeeById(Integer.MAX_VALUE);

        } catch (InvalidEmployeeIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving an employee from the database
    // when given an invalid id.
    public void getEmployeeByIdInvalidLowerBoundIdTest() {
        try {
            toTest.getEmployeeById(Integer.MIN_VALUE);

        } catch (InvalidEmployeeIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving a list of employees by a given last name
    // when given a null last name to check for.
    public void getEmployeesByNullLastNameTest() {
        try {
            List<Employee> employeesToCheck = toTest.getEmployeesByLastName(null);
        } catch (NullEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving a list of employees by a given last name
    // when given a empty last name to check for.
    public void getEmployeesByEmptyLastNameTest() {
        try {
            List<Employee> employeesToCheck = toTest.getEmployeesByLastName("");
        } catch (InvalidEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating an employee to the database
    // when given an null first name.
    public void editEmployeeNullFirstNameTest() {
        try {
            Employee editedEmployee = new Employee();
            editedEmployee.setFirstName(null);
            editedEmployee.setLastName("Schmo");
            toTest.editEmployee(1, editedEmployee);

        } catch (NullEmployeeFirstNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating an employee to the database
    // when given an null last name.
    public void editEmployeeNullLastNameTest() {
        try {
            Employee editedEmployee = new Employee();
            editedEmployee.setFirstName("Joe");
            editedEmployee.setLastName(null);
            toTest.editEmployee(1, editedEmployee);

        } catch (NullEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating an employee to the database
    // when given an empty first name.
    public void editEmployeeEmptyFirstNameTest() {
        try {
            Employee editedEmployee = new Employee();
            editedEmployee.setFirstName("");
            editedEmployee.setLastName("Schmo");
            toTest.editEmployee(1, editedEmployee);

        } catch (InvalidEmployeeFirstNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating an employee to the database
    // when given an empty last name.
    public void editEmployeeEmptyLastNameTest() {
        try {
            Employee editedEmployee = new Employee();
            editedEmployee.setFirstName("Joe");
            editedEmployee.setLastName("");
            toTest.editEmployee(1, editedEmployee);

        } catch (InvalidEmployeeLastNameException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles deleting an employee by id from the database
    // when given an null id.
    public void deleteEmployeeNullIdTest() {
        try {
            toTest.deleteEmployee(null);

        } catch (NullEmployeeIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles deleting an employee by id from the database
    // when given an invalid id.
    public void deleteEmployeeInvalidUpperBoundIdTest() {
        try {
            assertFalse(toTest.deleteEmployee(Integer.MAX_VALUE));

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles deleting an employee by id from the database
    // when given an invalid id.
    public void deleteEmployeeInvalidLowerBoundIdTest() {
        try {
            assertFalse(toTest.deleteEmployee(Integer.MIN_VALUE));

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

}
