package com.tp.staffing.daos;


import com.tp.staffing.exceptions.InvalidEmployeeIdException;
import com.tp.staffing.exceptions.InvalidPositionIdException;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.EmployeePostgresDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daoTesting")
class EmployeePostgresDaoTests {

    @Autowired
    EmployeePostgresDao toTest;

    @Autowired
    JdbcTemplate template;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {

        //Clearing all rows from the Employee, PositionDay, Position, and Day tables and restarting the
        //id sequence to begin at 1.
        template.update("TRUNCATE \"Employee\", \"PositionDay\", \"Position\", \"Day\" RESTART IDENTITY;");
        //Inserting values into the test database for testing purposes.
        template.update("INSERT INTO \"Employee\" (\"firstName\", \"lastName\") VALUES ('Noel', 'Castillo')");

    }

    @Test //Testing method to add an employee to the database. Golden path.
    public void newEmployeeGoldenPathTest() {
        try {
            Employee employeeToAdd = new Employee();
            employeeToAdd.setFirstName("Joe");
            employeeToAdd.setLastName("Schmo");

            Integer positionId = toTest.addEmployee(employeeToAdd);
            Employee addedEmployeeToCheck = toTest.getEmployeeById(2);


            assertEquals(2, addedEmployeeToCheck.getId());
            assertEquals("Joe", addedEmployeeToCheck.getFirstName());
            assertEquals("Schmo", addedEmployeeToCheck.getLastName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test //Testing method to retrieve an employee from the database by corresponding id. Golden path.
    public void getEmployeeByIdGoldenPathTest() {
        try {
            Employee addedEmployeeToCheck = toTest.getEmployeeById(1);

            assertEquals(1, addedEmployeeToCheck.getId());
            assertEquals("Noel", addedEmployeeToCheck.getFirstName());
            assertEquals("Castillo", addedEmployeeToCheck.getLastName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test //Testing method to retrieve all employees from database. Golden path.
    public void getEmployeesGoldenPath() {
        List<Employee> employeesToCheck = toTest.getEmployees();

        assertEquals(1, employeesToCheck.get(0).getId());
        assertEquals("Noel", employeesToCheck.get(0).getFirstName());
        assertEquals("Castillo", employeesToCheck.get(0).getLastName());
    }

    @Test //Testing method to retrieve all employees from the database by last name. Golden path.
    public void getEmployeesByLastNameGoldenPath() {
        List<Employee> employeesToCheck = toTest.getEmployeesByLastName("Castillo");

        assertEquals(1, employeesToCheck.get(0).getId());
        assertEquals("Noel", employeesToCheck.get(0).getFirstName());
        assertEquals("Castillo", employeesToCheck.get(0).getLastName());
    }

    @Test //Testing method to update an existing employee in the database
    // by a given employee and corresponding id. Golden path.
    public void updateEmployeeGoldenPathTest() {
        try {

            Employee employeeToUpdate = toTest.getEmployeeById(1);
            employeeToUpdate.setFirstName("Joe");
            employeeToUpdate.setLastName("Schmo");
            toTest.editEmployee(1, employeeToUpdate);

            Employee updatedEmployee = toTest.getEmployeeById(1);

            assertEquals(1, updatedEmployee.getId());
            assertEquals("Joe", updatedEmployee.getFirstName());
            assertEquals("Schmo", updatedEmployee.getLastName());
        } catch (Exception e) {
            fail();
        }

    }

    @Test //Testing method to delete an employee from the database by corresponding id. Golden path.
    public void deleteEmployeeGoldenPathTest() {
        try {

            assertNotNull(toTest.getEmployeeById(1));
            toTest.deleteEmployee(1);
            assertNull(toTest.getEmployeeById(1));

        } catch (Exception e) {
            fail();
        }
    }

    @Test //Testing method to delete an employee from the database with an ID that does not exist in the database.
    public void deleteEmployeeInvalidUpperBoundIdTest() {
        assertFalse(toTest.deleteEmployee(Integer.MAX_VALUE));
    }

    @Test //Testing method to delete an employee from the database with an ID that does not exist in the database.
    public void deleteEmployeeInvalidLowerBoundIdTest() {
        assertFalse(toTest.deleteEmployee(Integer.MIN_VALUE));
    }


}
