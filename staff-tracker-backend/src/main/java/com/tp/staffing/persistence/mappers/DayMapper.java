package com.tp.staffing.persistence.mappers;

import com.tp.staffing.model.Day;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class DayMapper implements RowMapper<Day> {

    @Override
    public Day mapRow(ResultSet resultSet, int i) throws SQLException {
        Day mappedDay = new Day();
        mappedDay.setId(resultSet.getInt("id"));
        mappedDay.setDate(resultSet.getDate("date").toLocalDate());
        return mappedDay;
    }
}
