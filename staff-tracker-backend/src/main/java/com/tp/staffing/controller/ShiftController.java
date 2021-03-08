package com.tp.staffing.controller;


import com.tp.staffing.exceptions.InvalidShiftIdException;
import com.tp.staffing.exceptions.NullShiftIdException;
import com.tp.staffing.exceptions.NullShiftNameException;
import com.tp.staffing.exceptions.NullShiftTimeException;
import com.tp.staffing.model.Shift;
import com.tp.staffing.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class ShiftController {
    @Autowired
    ShiftService service;

    //Adds a new shift to the database by the given shift. name/startTime/endTime cannot be null.
    @PostMapping("/shifts/add-shift")
    public Integer addShift(@RequestBody Shift shift) throws NullShiftNameException, NullShiftTimeException {
        return service.addShift(shift);
    }

    //Retrieves a Shift from the database by the given id.
    @GetMapping("/shift/{id}")
    public Shift getShiftById(@PathVariable Integer id) throws NullShiftIdException, InvalidShiftIdException {
        return service.getShiftById(id);
    }

    //Retrieves a list of all Shifts in the database.
    @GetMapping("/shifts")
    public List<Shift> getShifts() {
        return service.getShifts();
    }

    //Edits an existing Shift in the database by replacing it's attributes with the
    //attributes of the given Shift. This is done on the Shift with the given id.
    @PutMapping("/shifts/{id}")
    public String editShift(@PathVariable Integer id, @RequestBody Shift shift) throws NullShiftNameException, NullShiftTimeException {
        if (service.editShift(id, shift)) {
            return "Shift " + id + " updated";
        } else {
            return "Shift " + id + " not found";
        }

    }

    //Deletes an existing Shift row from the database.
    @DeleteMapping("/shifts/delete/{id}")
    public String deleteShift(@PathVariable Integer id) throws NullShiftIdException, InvalidShiftIdException {
        if (service.deleteShift(id)) {
            return "Shift " + id + " deleted";
        } else {
            return "Shift " + id + " not found";
        }
    }
}
