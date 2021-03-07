package com.tp.staffing.persistence;

import com.tp.staffing.model.Shift;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.ShiftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShiftPostgresDao implements ShiftDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Adds a Shift to the database and returns it's id.
    public Integer addShift(Shift shift) {

        return template.query("INSERT INTO public.\"Shift\"(\"name\", \"startTime\", \"endTime\")" +
                "VALUES ( '" + shift.getName() + "', '" + shift.getStartTime() + "', '" + shift.getEndTime() + "') " +
                "RETURNING \"id\";", new IdMapper()).get(0);
    }

    @Override //Returns a Shift from the database with the given id.
    // Or returns null if no Shifts are found with the given id.
    public Shift getShiftById(Integer id) {
        List<Shift> Shifts = template.query("SELECT * " +
                "\tFROM public.\"Shift\"\n" +
                "\t\tWHERE \"id\" = '" + id + "';", new ShiftMapper());

        if (Shifts.isEmpty()) {
            return null;
        }

        return Shifts.get(0);
    }

    @Override //Returns a list of all Shifts in the database.
    public List<Shift> getShifts() {
        List<Shift> Shifts = template.query("SELECT * " +
                "\tFROM public.\"Shift\"\n;", new ShiftMapper());

        if (Shifts.isEmpty()) {
            return null;
        }

        return Shifts;
    }


    @Override //Returns true or false if updating a Shift was successful.
    public boolean editShift(Integer id, Shift updatedShift) {
        if (getShiftById(id) == null) {
            return false;
        } else {
            template.execute("UPDATE public.\"Shift\" " +
                    "SET \"name\"='" + updatedShift.getName() +
                    "', \"startTime\"='" + updatedShift.getStartTime() +
                    "', \"endTime\"='" + updatedShift.getEndTime() + "' " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }

    @Override //Returns true or false if deleting a Shift was successful.
    public boolean deleteShift(Integer id) {

        if (getShiftById(id) == null) {
            return false;
        } else {
            template.execute("DELETE FROM public.\"Shift\" " +
                    "WHERE \"id\" = " + id + ";");
            return true;
        }
    }


}
