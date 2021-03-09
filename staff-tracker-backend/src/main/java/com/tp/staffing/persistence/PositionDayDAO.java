package com.tp.staffing.persistence;


import com.tp.staffing.exceptions.InvalidPositionIdException;
import com.tp.staffing.model.Position;

import java.util.List;

public interface PositionDayDAO {

    boolean addPositionDay(Integer positionId, Integer dayId);

    boolean deletePositionDay(Integer positionId, Integer dayId);

}
