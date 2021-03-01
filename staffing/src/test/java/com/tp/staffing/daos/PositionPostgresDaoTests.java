package com.tp.staffing.daos;


import com.tp.staffing.exceptions.NullPositionIdException;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.PositionPostgresDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("daoTesting")
class PositionPostgresDaoTests {

    @Autowired
    PositionPostgresDao toTest;

    @Autowired
    JdbcTemplate template;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {

        //Clearing all rows from the Employee, PositionDay, Position, and Day tables and restarting the
        //id sequence to begin at 1.
        template.update("TRUNCATE \"Employee\", \"PositionDay\", \"Position\", \"Day\" RESTART IDENTITY;");
        //Inserting values into the test database for testing purposes.
        template.update("INSERT INTO \"Position\" (\"title\") VALUES ('Server')");

    }

    @Test //Testing method to add a new position to the database. Golden path.
    public void newPositionGoldenPath() {

        Position positionToAdd = new Position();
        positionToAdd.setTitle("Owner");

        Integer positionId = toTest.addPosition(positionToAdd);
        Position addedPositionToCheck = toTest.getPositionById(2);


        assertEquals(2, addedPositionToCheck.getId());
        assertEquals("Owner", addedPositionToCheck.getTitle());


        List<Position> allPositions = toTest.getPositions();

        assertEquals(2, allPositions.get(1).getId());
        assertEquals("Owner", allPositions.get(1).getTitle());

    }

    @Test //Testing method to retrieve a position from the database by corresponding id. Golden path.
    public void getPositionByIdGoldenPath() {
        Position positionToCheck = toTest.getPositionById(1);

        assertEquals(1, positionToCheck.getId());
        assertEquals("Server", positionToCheck.getTitle());


    }

    @Test //Testing method to retrieve all positions from the database. Golden path.
    public void getPositionsGoldenPath() {
        List<Position> positionsToCheck = toTest.getPositions();

        assertEquals(1, positionsToCheck.get(0).getId());
        assertEquals("Server", positionsToCheck.get(0).getTitle());
    }

    @Test //Testing method to retrieve all positions from the database by a given title. Golden path.
    public void getPositionsByTitleGoldenPath() {
        List<Position> positionsToCheck = toTest.getPositionsByTitle("Server");

        assertEquals(1, positionsToCheck.get(0).getId());
        assertEquals("Server", positionsToCheck.get(0).getTitle());
    }

    @Test //Testing method to update an existing position in the database
    // by a given position and corresponding id. Golden path.
    public void updatePositionGoldenPathTest() {
        Position positionToUpdate = toTest.getPositionById(1);
        positionToUpdate.setTitle("Manager");
        toTest.editPosition(1, positionToUpdate);

        Position updatedPosition = toTest.getPositionById(1);

        assertEquals(1, updatedPosition.getId());
        assertEquals("Manager", updatedPosition.getTitle());

    }

    @Test //Testing method to delete a position from the database by its corresponding id. Golden path.
    public void deletePositionGoldenPathTest() {
        assertNotNull(toTest.getPositionById(1));
        toTest.deletePosition(1);
        assertNull(toTest.getPositionById(1));

    }

    @Test //Testing method to delete a position from the database with an ID that does not exist in the database.
    public void deletePositionInvalidUpperBoundIdTest() {
        assertFalse(toTest.deletePosition(Integer.MAX_VALUE));
    }

    @Test //Testing method to delete a position from the database with an ID that does not exist in the database.
    public void deletePositionInvalidLowerBoundIdTest() {
        assertFalse(toTest.deletePosition(Integer.MIN_VALUE));

    }

    @Test //Testing method to add an employee id to a position by the given corresponding employee id and position id.
    //Golden path.
    public void addEmployeeToPositionGoldenPathTest() {
        template.update("INSERT INTO \"Employee\" (\"firstName\", \"lastName\") VALUES ('Noel', 'Castillo')");
        toTest.addEmployeeToPosition(1, 1);
        assertEquals(1, toTest.getPositionById(1).getEmployeeId());
    }

    @Test //Testing method to remove any associated employee id of a position by the given id.
    public void removeEmployeeToPositionGoldenPathTest() {
        template.update("INSERT INTO \"Employee\" (\"firstName\", \"lastName\") VALUES ('Noel', 'Castillo')");
        toTest.addEmployeeToPosition(1, 1);
        assertEquals(1, toTest.getPositionById(1).getEmployeeId());
        toTest.removeEmployeeFromPosition(1);
        assertEquals(0, toTest.getPositionById(1).getEmployeeId());
    }

}
