package com.tp.staffing.daos;


import com.tp.staffing.model.Position;
import com.tp.staffing.model.Shift;
import com.tp.staffing.persistence.PositionPostgresDao;
import com.tp.staffing.persistence.ShiftPostgresDao;
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
class ShiftPostgresDaoTests {

    @Autowired
    ShiftPostgresDao toTest;

    @Autowired
    JdbcTemplate template;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {

        //Clearing all rows from the Employee, PositionDay, Position, Day, and Shift tables and restarting the
        //id sequence to begin at 1.
        template.update("TRUNCATE \"Employee\", \"PositionDay\", \"Position\", \"Day\", \"Shift\" RESTART IDENTITY;");
        //Inserting values into the test database for testing purposes.
        template.update("INSERT INTO \"Shift\" (\"name\", \"startTime\", \"endTime\") VALUES ('Morning', 7, 15)");

    }

    @Test //Testing method to add a new shift to the database. Golden path.
    public void newShiftGoldenPath() {

        Shift shiftToAdd = new Shift();
        shiftToAdd.setName("Breakfast");
        shiftToAdd.setStartTime(6);
        shiftToAdd.setEndTime(11);

        Integer shiftId = toTest.addShift(shiftToAdd);
        Shift addedShiftToCheck = toTest.getShiftById(2);


        assertEquals(2, addedShiftToCheck.getId());
        assertEquals("Breakfast", addedShiftToCheck.getName());
        assertEquals(6, addedShiftToCheck.getStartTime());
        assertEquals(11, addedShiftToCheck.getEndTime());


        List<Shift> allShifts = toTest.getShifts();

        assertEquals(2, allShifts.get(1).getId());
        assertEquals("Breakfast", allShifts.get(1).getName());
        assertEquals(6, allShifts.get(1).getStartTime());
        assertEquals(11, allShifts.get(1).getEndTime());

    }

    @Test //Testing method to retrieve a shift from the database by corresponding id. Golden path.
    public void getShiftByIdGoldenPath() {
        Shift shiftToCheck = toTest.getShiftById(1);

        assertEquals(1, shiftToCheck.getId());
        assertEquals("Morning", shiftToCheck.getName());


    }

    @Test //Testing method to retrieve all shifts from the database. Golden path.
    public void getShiftsGoldenPath() {
        List<Shift> shiftToCheck = toTest.getShifts();

        assertEquals(1, shiftToCheck.get(0).getId());
        assertEquals("Morning", shiftToCheck.get(0).getName());
    }


    @Test //Testing method to update an existing shift in the database
    // by a given shift and corresponding id. Golden path.
    public void updateShiftGoldenPathTest() {
        Shift shiftToUpdate = toTest.getShiftById(1);
        shiftToUpdate.setName("Day");
        toTest.editShift(1, shiftToUpdate);

        Shift updatedShift = toTest.getShiftById(1);

        assertEquals(1, updatedShift.getId());
        assertEquals("Day", updatedShift.getName());

    }

    @Test //Testing method to delete a shift from the database by its corresponding id. Golden path.
    public void deleteShiftGoldenPathTest() {
        assertNotNull(toTest.getShiftById(1));
        toTest.deleteShift(1);
        assertNull(toTest.getShiftById(1));

    }

    @Test //Testing method to delete a position from the database with an ID that does not exist in the database.
    public void deletePositionInvalidUpperBoundIdTest() {
        assertFalse(toTest.deleteShift(Integer.MAX_VALUE));
    }

    @Test //Testing method to delete a position from the database with an ID that does not exist in the database.
    public void deletePositionInvalidLowerBoundIdTest() {
        assertFalse(toTest.deleteShift(Integer.MIN_VALUE));

    }

}
