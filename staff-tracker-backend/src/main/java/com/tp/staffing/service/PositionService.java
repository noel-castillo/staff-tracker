package com.tp.staffing.service;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.PositionPostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionPostgresDao dao;

    //Adds a position to the database.
    //When attempting to add a position to the database. An exception will be thrown if the given title
    //variable is either null or empty.
    public Integer addPosition(Position position) throws NullPositionTitleException, InvalidPositionTitleException {
        if (position.getTitle() == null) {
            throw new NullPositionTitleException("You cannot add a Position with a null title.");
        }

        if (position.getTitle().trim().equals("")) {
            throw new InvalidPositionTitleException("You cannot add a Position with a empty title.");
        }
        return dao.addPosition(position);
    }

    //Retrieves a position from the database by id.
    //When attempting to retrieve an id from the database. An exception will be thrown if given
    //a null for the id to look for.
    public Position getPositionById(Integer id) throws NullPositionIdException, InvalidPositionIdException {
        if (id == null) {
            throw new NullPositionIdException("You cannot retrieve a Position with null id.");
        }

        Position positionToReturn = dao.getPositionById(id);
        if(positionToReturn == null){
            throw new InvalidPositionIdException("No Position with that ID exists.");
        } else{
            return positionToReturn;
        }

    }

    //Retrieves a list of positions by title. When getting a list of positions from the database by a given title, an exception will be
    //thrown if the given title to check for is either null or empty.
    public List<Position> getPositionsByTitle(String title) throws NullPositionTitleException, InvalidPositionTitleException {
        if (title == null) {
            throw new NullPositionTitleException("You cannot retrieve Positions with a null title.");
        }

        if (title.trim().equals("")) {
            throw new InvalidPositionTitleException("You cannot retrieve Positions with a empty title.");
        }
        return dao.getPositionsByTitle(title);
    }

    //Retrieves a list of all existing positions in the database.
    public List<Position> getPositions() {
        return dao.getPositions();
    }

    //Updates an existing position in the database.
    //An exception will be thrown if attempting to update a position to have a
    //null or empty string for the title.
    public boolean editPosition(Integer id, Position position) throws InvalidPositionTitleException, NullPositionTitleException, InvalidPositionIdException {
        if (position.getTitle() == null) {
            throw new NullPositionTitleException("You cannot edit a Position to have a null title.");
        }

        if (position.getTitle().trim().equals("")) {
            throw new InvalidPositionTitleException("You cannot edit a Position with a empty title.");
        }
        return dao.editPosition(id, position);
    }

    //Deletes ane existing position from the database.
    //An exception will be thrown if given a null for the id to check for when deleting.
    public boolean deletePosition(Integer id) throws NullPositionIdException, InvalidPositionIdException {
        if (id == null) {
            throw new NullPositionIdException("You cannot delete a Position with null id.");
        }
        return dao.deletePosition(id);
    }

    //Adds an employee id to a position to establish a relationship.
    //An exception will be thrown if either the employee id or position id are null
    public boolean addEmployeeToPosition(Integer employeeId, Integer positionId) throws NullEmployeeIdException, NullPositionIdException, InvalidPositionIdException {
        if (employeeId == null) {
            throw new NullEmployeeIdException("You cannot retrieve an Employee with null id.");
        }

        if (positionId == null) {
            throw new NullPositionIdException("You cannot retrieve a Position with null id.");
        }
        return dao.addEmployeeToPosition(employeeId, positionId);
    }


    //Removes any existing employee id from a position by the given position id.
    //An exception will be thrown if given a null for the position id.
    public boolean removeEmployeeFromPosition(Integer id) throws NullPositionIdException, InvalidPositionIdException {
        if (id == null) {
            throw new NullPositionIdException("Cannot locate a Position with null id.");
        }
        return dao.removeEmployeeFromPosition(id);
    }
}
