package com.tp.staffing.controller;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    PositionService service;

    //Adds a new position to the database by the given position. Title cannot be null or empty.
    @PostMapping("/positions/add-position")
    public Integer addPosition(@RequestBody Position position) throws NullPositionTitleException, InvalidPositionTitleException {
        return service.addPosition(position);
    }

    //Retrieves a position from the database by the given id.
    @GetMapping("/position/{id}")
    public Position getPositionById(@PathVariable Integer id) throws NullPositionIdException, InvalidPositionIdException {
        return service.getPositionById(id);
    }

    //Retrieves a list of positions by the given title from the database. Title cannot be null or empty.
    @GetMapping("/positions/{title}")
    public List<Position> getPositionsByTitle(@PathVariable String title) throws NullPositionTitleException, InvalidPositionTitleException {
        return service.getPositionsByTitle(title);
    }

    //Retrieves a list of all positions in the database.
    @GetMapping("/positions")
    public List<Position> getPositions() {
        return service.getPositions();
    }

    //Edits an existing position in the database by replacing it's attributes with the
    //attributes of the given position. This is done on the position with the given id.
    @PutMapping("/positions/{id}")
    public String editPosition(@PathVariable Integer id, @RequestBody Position position) throws InvalidPositionTitleException, NullPositionTitleException, InvalidPositionIdException {
        if (service.editPosition(id, position)) {
            return "Position " + id + " updated";
        } else {
            return "Position " + id + " not found";
        }

    }

    //Deletes an existing position row from the database.
    @DeleteMapping("/positions/delete/{id}")
    public String deletePosition(@PathVariable Integer id) throws NullPositionIdException, InvalidPositionIdException {
        if (service.deletePosition(id)) {
            return "Position " + id + " deleted";
        } else {
            return "Position " + id + " not found";
        }
    }

    //Assigns an employee's id to the foreign key variable to the corresponding position row based on
    //the given position id.
    @PutMapping("/employee/{employeeId}/position/{positionId}")
    public String addEmployeeToPosition(@PathVariable Integer employeeId, @PathVariable Integer positionId) throws NullEmployeeIdException, NullPositionIdException, InvalidPositionIdException {
        if (service.addEmployeeToPosition(employeeId, positionId)) {
            return "Employee " + employeeId + " has taken Position " + positionId;
        } else {
            return "Unable to locate associated Employee " + employeeId + " or Position " + positionId;
        }
    }

    //Removes any existing employeeId from the position row for a given position id.
    @PutMapping("/position/{id}/clear")
    public String removeEmployeeFromPosition(@PathVariable Integer id) throws InvalidPositionIdException, NullPositionIdException {
        if (service.removeEmployeeFromPosition(id)) {
            return "Position " + id + " is now vacant.";
        } else {
            return "Unable to locate associated Position " + id + ".";
        }
    }

}
