package com.tp.staffing.daos;


import com.tp.staffing.model.Week;
import com.tp.staffing.persistence.WeekPostgresDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daoTesting")
class WeekPostgresDaoTests {

    @Autowired
    WeekPostgresDao toTest;

    @Autowired
    JdbcTemplate template;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {

        //Clearing all rows from the Employee, PositionDay, Position, Day, and Week tables and restarting the
        //id sequence to begin at 1.
        template.update("TRUNCATE \"Employee\", \"PositionDay\", \"Position\", \"Shift\", \"Day\", \"Week\" RESTART IDENTITY;");
        //Inserting values into the test database for testing purposes.
        template.update("INSERT INTO \"Week\" (\"startDate\", \"endDate\") VALUES ('2021-03-07', '2021-03-13')");

    }

    @Test //Testing method to add a new Week to the database. Golden path.
    public void newWeekGoldenPath() {

        Week weekToAdd = new Week();
        weekToAdd.setStartDate(LocalDate.of(2021, 2, 28));
        weekToAdd.setEndDate(LocalDate.of(2021, 3, 6));

        Integer WeekId = toTest.addWeek(weekToAdd);
        Week addedWeekToCheck = toTest.getWeekById(2);


        assertEquals(2, addedWeekToCheck.getId());
        assertEquals(LocalDate.of(2021, 2, 28), addedWeekToCheck.getStartDate());
        assertEquals(LocalDate.of(2021, 3, 6), addedWeekToCheck.getEndDate());


        List<Week> allWeeks = toTest.getWeeks();

        assertEquals(2, allWeeks.get(1).getId());
        assertEquals(LocalDate.of(2021, 2, 28), allWeeks.get(1).getStartDate());
        assertEquals(LocalDate.of(2021, 3, 6), allWeeks.get(1).getEndDate());

    }

    @Test //Testing method to retrieve a Week from the database by corresponding id. Golden path.
    public void getWeekByIdGoldenPath() {
        Week weekToCheck = toTest.getWeekById(1);

        assertEquals(1, weekToCheck.getId());
        assertEquals(LocalDate.of(2021, 3, 7), weekToCheck.getStartDate());
        assertEquals(LocalDate.of(2021, 3, 13), weekToCheck.getEndDate());


    }

    @Test //Testing method to retrieve all Weeks from the database. Golden path.
    public void getWeeksGoldenPath() {
        List<Week> weekToCheck = toTest.getWeeks();

        assertEquals(1, weekToCheck.get(0).getId());
        assertEquals(LocalDate.of(2021, 3, 7), weekToCheck.get(0).getStartDate());
        assertEquals(LocalDate.of(2021, 3, 13), weekToCheck.get(0).getEndDate());
    }


    @Test //Testing method to update an existing Week in the database
    // by a given Week and corresponding id. Golden path.
    public void updateWeekGoldenPathTest() {
        Week weekToUpdate = toTest.getWeekById(1);
        weekToUpdate.setStartDate(LocalDate.of(2021, 3, 8));
        weekToUpdate.setEndDate(LocalDate.of(2021, 3, 12));
        toTest.editWeek(1, weekToUpdate);

        Week updatedWeek = toTest.getWeekById(1);

        assertEquals(1, updatedWeek.getId());
        assertEquals(LocalDate.of(2021, 3, 8), updatedWeek.getStartDate());
        assertEquals(LocalDate.of(2021, 3, 12), updatedWeek.getEndDate());

    }

    @Test //Testing method to delete a Week from the database by its corresponding id. Golden path.
    public void deleteWeekGoldenPathTest() {
        assertNotNull(toTest.getWeekById(1));
        toTest.deleteWeek(1);
        assertNull(toTest.getWeekById(1));

    }

    @Test //Testing method to delete a week from the database with an ID that does not exist in the database.
    public void deleteWeekInvalidUpperBoundIdTest() {
        assertFalse(toTest.deleteWeek(Integer.MAX_VALUE));
    }

    @Test //Testing method to delete a week from the database with an ID that does not exist in the database.
    public void deleteWeekInvalidLowerBoundIdTest() {
        assertFalse(toTest.deleteWeek(Integer.MIN_VALUE));

    }

}
