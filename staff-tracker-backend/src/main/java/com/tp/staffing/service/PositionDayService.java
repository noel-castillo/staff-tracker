package com.tp.staffing.service;


import com.tp.staffing.exceptions.InvalidPositionTitleException;
import com.tp.staffing.exceptions.NullPositionTitleException;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.PositionDayPostgresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionDayService {

    @Autowired
    PositionDayPostgresDao dao;

    public boolean addPositionDay(Integer positionId, Integer dayId)  {
        return dao.addPositionDay(positionId, dayId);
    }

    public boolean deletePositionDay(Integer positionId, Integer dayId)  {
        return dao.deletePositionDay(positionId, dayId);
    }


}
