package com.tp.staffing.persistence;


import com.tp.staffing.exceptions.InvalidPositionIdException;
import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;

import java.util.List;

public interface PositionDAO {

    Position getPositionById(Integer id) throws InvalidPositionIdException;

    List<Position> getPositionsByTitle(String title);

    List<Position> getPositions();

    Integer addPosition(Position position);

    boolean deletePosition(Integer id) throws InvalidPositionIdException;

    boolean editPosition(Integer id, Position updatedPosition) throws InvalidPositionIdException;

    boolean addEmployeeToPosition(Integer employeeId, Integer positionId) throws InvalidPositionIdException;

    boolean removeEmployeeFromPosition(Integer id) throws InvalidPositionIdException;

}
