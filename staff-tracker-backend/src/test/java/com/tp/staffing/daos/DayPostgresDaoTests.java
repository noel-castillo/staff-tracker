package com.tp.staffing.daos;


import com.tp.staffing.model.Day;
import com.tp.staffing.persistence.DayPostgresDao;
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
class DayPostgresDaoTests {

    @Autowired
    DayPostgresDao toTest;

    @Autowired
    JdbcTemplate template;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {

        //Clearing all rows from the Employee, PositionDay, Day, and Day tables and restarting the
        //id sequence to begin at 1.
        template.update("TRUNCATE \"Employee\", \"PositionDay\", \"Position\", \"Day\" RESTART IDENTITY;");
        //Inserting values into the test database for testing purposes.
        template.update("INSERT INTO \"Day\" (\"date\") VALUES ('2021-03-07')");

    }

    @Test //Testing method to add a new Day to the database. Golden path.
    public void newDayGoldenPath() {

        Day dayToAdd = new Day();
        dayToAdd.setDate(LocalDate.of(2021, 3, 8));

        Integer dayId = toTest.addDay(dayToAdd);
        Day addedDayToCheck = toTest.getDayById(2);


        assertEquals(2, addedDayToCheck.getId());
        assertEquals(LocalDate.of(2021, 3, 8), addedDayToCheck.getDate());


        List<Day> allDays = toTest.getDays();

        assertEquals(2, allDays.get(1).getId());
        assertEquals(LocalDate.of(2021, 3, 8), allDays.get(1).getDate());

    }

    @Test //Testing method to retrieve a Day from the database by corresponding id. Golden path.
    public void getDayByIdGoldenPath() {
        Day dayToCheck = toTest.getDayById(1);

        assertEquals(1, dayToCheck.getId());
        assertEquals(LocalDate.of(2021, 3, 7), dayToCheck.getDate());


    }

    @Test //Testing method to retrieve all Days from the database. Golden path.
    public void getDaysGoldenPath() {
        List<Day> daysToCheck = toTest.getDays();

        assertEquals(1, daysToCheck.get(0).getId());
        assertEquals(LocalDate.of(2021, 3, 7), daysToCheck.get(0).getDate());
    }

    @Test //Testing method to retrieve all Days from the database by a given range. Golden path.
    public void getDaysByRangeGoldenPath() {
        List<Day> daysToCheck = toTest.getDaysByRange(LocalDate.of(2021, 3, 1),
                LocalDate.of(2021, 3, 7));

        assertEquals(1, daysToCheck.get(0).getId());
        assertEquals(LocalDate.of(2021, 3, 7), daysToCheck.get(0).getDate());
    }

    @Test //Testing method to update an existing Day in the database
    // by a given Day and corresponding id. Golden path.
    public void updateDayGoldenPathTest() {
        Day dayToUpdate = toTest.getDayById(1);
        dayToUpdate.setDate(LocalDate.of(2021, 3, 5));
        toTest.editDay(1, dayToUpdate);

        Day updatedDay = toTest.getDayById(1);

        assertEquals(1, updatedDay.getId());
        assertEquals(LocalDate.of(2021, 3, 5), updatedDay.getDate());

    }

    @Test //Testing method to delete a Day from the database by its corresponding id. Golden path.
    public void deleteDayGoldenPathTest() {
        assertNotNull(toTest.getDayById(1));
        toTest.deleteDay(1);
        assertNull(toTest.getDayById(1));

    }

    @Test //Testing method to delete a Day from the database with an ID that does not exist in the database.
    public void deleteDayInvalidUpperBoundIdTest() {
        assertFalse(toTest.deleteDay(Integer.MAX_VALUE));
    }

    @Test //Testing method to delete a Day from the database with an ID that does not exist in the database.
    public void deleteDayInvalidLowerBoundIdTest() {
        assertFalse(toTest.deleteDay(Integer.MIN_VALUE));

    }


}
