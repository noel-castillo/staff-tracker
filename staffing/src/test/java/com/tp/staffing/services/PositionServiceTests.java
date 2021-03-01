package com.tp.staffing.services;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.service.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class PositionServiceTests {

    @Autowired
    PositionService toTest;


    //this will run before each @Test method
    @BeforeEach
    public void setup() {
        //None of these tests should reach into the database as exceptions should be thrown
        //prior to that. Nothing to setup for.
    }

    @Test //Testing method properly handles adding a position to the database
    // when given a null title for the position.
    public void addPositionNullTitleTest() {
        try {
            Position positionToAdd = new Position();
            positionToAdd.setTitle(null);
            toTest.addPosition(positionToAdd);

        } catch (NullPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles adding a position to the database
    // when given a empty title for the position.
    public void addPositionEmptyTitleTest() {
        try {
            Position positionToAdd = new Position();
            positionToAdd.setTitle("");
            toTest.addPosition(positionToAdd);

        } catch (InvalidPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving a position by id from the database
    // when given a null value for id to check for.
    public void getPositionByIdNullIdTest() {
        try {
            toTest.getPositionById(null);
        } catch (NullPositionIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            fail();
        }

    }

    @Test //Testing method properly handles retrieving a position by id from the database
    // when given a invalid id to check for.
    public void getPositionByIdInvalidUpperBoundIdTest() {
        try {
            toTest.getPositionById(Integer.MAX_VALUE);
        } catch (InvalidPositionIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            fail();
        }

    }

    @Test //Testing method properly handles retrieving a position by id from the database
    // when given a invalid id to check for.
    public void getPositionByIdInvalidLowerBoundIdTest() {
        try {
            toTest.getPositionById(Integer.MIN_VALUE);
        } catch (InvalidPositionIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            fail();
        }
    }

    @Test //Testing method properly handles retrieving a list of positions from the database
    // when given a null title to check for.
    public void getPositionsByNullTitleTest() {
        try {
            List<Position> positionsToCheck = toTest.getPositionsByTitle(null);
        } catch (NullPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles retrieving a list of positions from the database
    // when given a empty title to check for.
    public void getPositionsByEmptyTitleTest() {
        try {
            List<Position> positionsToCheck = toTest.getPositionsByTitle("");
        } catch (InvalidPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating a position in the database
    // when given a null title.
    public void editPositionNullTitleTest() {
        try {
            Position editedPosition = new Position();
            editedPosition.setTitle(null);
            toTest.editPosition(1, editedPosition);

        } catch (NullPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles updating a position in the database
    // when given a empty title.
    public void editPositionEmptyTitleTest() {
        try {
            Position editedPosition = new Position();
            editedPosition.setTitle("");
            toTest.editPosition(1, editedPosition);

        } catch (InvalidPositionTitleException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test //Testing method properly handles deleting a position by id from the database
    // when given a null id.
    public void deletePositionNullIdTest() {
        try {
            toTest.deletePosition(null);
        } catch (NullPositionIdException e) {
            //Nothing done since we want this.
        } catch (Exception e) {
            fail();
        }

    }

    @Test //Testing method properly handles deleting a position by id from the database
    // when given an invalid id.
    public void deletePositionInvalidUpperBoundIdTest() {
        try {
            assertFalse(toTest.deletePosition(Integer.MAX_VALUE));
        } catch (Exception e) {
            fail();
        }
    }

    @Test //Testing method properly handles deleting a position by id from the database
    // when given an invalid id.
    public void deletePositionInvalidLowerBoundIdTest() {
        try {
            assertFalse(toTest.deletePosition(Integer.MIN_VALUE));
        } catch (Exception e) {
            fail();
        }
    }
}
