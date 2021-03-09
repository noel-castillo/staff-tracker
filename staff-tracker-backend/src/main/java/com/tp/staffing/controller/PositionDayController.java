package com.tp.staffing.controller;


import com.tp.staffing.exceptions.*;
import com.tp.staffing.model.Position;
import com.tp.staffing.service.PositionDayService;
import com.tp.staffing.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins="http://localhost:4200")
public class PositionDayController {
    @Autowired
    PositionDayService service;


    //Adds a new PositionDay relationship to the database by the given position and day. Title cannot be null or empty.
    @PostMapping("/position-day/{positionId}/{dayId}")
    public String addPositionDay(@PathVariable Integer positionId, @PathVariable Integer dayId)  {
        if(service.addPositionDay(positionId, dayId)){
            return "PositionDay " + positionId + " : " + dayId + " created";
        } else {
            return  "PositionDay " + positionId + " : " + dayId + " not created";
        }
    }

    //Deletes an existing PositionDay relationship from the database.
    @DeleteMapping("/position-day/{positionId}/{dayId}")
    public String deletePosition(@PathVariable Integer positionId, @PathVariable Integer dayId)  {
        if (service.deletePositionDay(positionId, dayId)) {
            return "PositionDay " + positionId + " : " + dayId + " deleted";
        } else {
            return  "PositionDay " + positionId + " : " + dayId + " not found";
        }
    }
}
