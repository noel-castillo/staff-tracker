package com.tp.staffing.persistence;

import com.tp.staffing.model.Employee;
import com.tp.staffing.model.Position;
import com.tp.staffing.persistence.mappers.EmployeeMapper;
import com.tp.staffing.persistence.mappers.IdMapper;
import com.tp.staffing.persistence.mappers.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionDayPostgresDao implements PositionDayDAO {


    @Autowired
    private JdbcTemplate template;

    @Override  //Returns true or false if creating a PositionDay relationship was successful.
    public boolean addPositionDay(Integer positionId, Integer dayId) {
        try {
            template.execute("INSERT INTO public.\"PositionDay\"(\"positionId\", \"dayId\")" +
                    "VALUES (" + positionId + ", " + dayId + ") " +
                    "RETURNING \"id\";");
            return true;
        } catch(Exception e){
            return false;
        }
    }


    @Override //Returns true or false if deleting a PositionDay relationship was successful.
    public boolean deletePositionDay(Integer positionId, Integer dayId) {
//        PositionPostgresDao
//
//        if (getPositionById(positionId) == null) {
//            return false;
//        } else {
//            template.execute("DELETE FROM public.\"PositionDay\" " +
//                    "WHERE \"id\" = " + id + ";");
//            return true;
//        }
        return false;
    }


}
