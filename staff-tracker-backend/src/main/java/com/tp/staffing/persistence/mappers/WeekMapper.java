package com.tp.staffing.persistence.mappers;

import com.tp.staffing.model.Week;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeekMapper implements RowMapper<Week> {

    @Override
    public Week mapRow(ResultSet resultSet, int i) throws SQLException {
        Week mappedWeek = new Week();
        mappedWeek.setId(resultSet.getInt("id"));
        mappedWeek.setStartDate(resultSet.getDate("startDate").toLocalDate());
        mappedWeek.setEndDate(resultSet.getDate("endDate").toLocalDate());
        return mappedWeek;
    }
}
