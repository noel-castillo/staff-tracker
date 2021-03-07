package com.tp.staffing.persistence.mappers;

import com.tp.staffing.model.Shift;
import com.tp.staffing.model.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftMapper implements RowMapper<Shift> {

    @Override
    public Shift mapRow(ResultSet resultSet, int i) throws SQLException {
        Shift mappedShift = new Shift();
        mappedShift.setId(resultSet.getInt("id"));
        mappedShift.setName(resultSet.getString("name"));
        mappedShift.setStartTime(resultSet.getInt("startTime"));
        mappedShift.setEndTime(resultSet.getInt("endTime"));

        return mappedShift;
    }
}
