package com.tp.staffing.persistence.mappers;

import com.tp.staffing.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee mappedEmployee = new Employee();
        mappedEmployee.setId(resultSet.getInt("id"));
        mappedEmployee.setFirstName(resultSet.getString("firstName"));
        mappedEmployee.setLastName(resultSet.getString("lastName"));
        mappedEmployee.setEmail(resultSet.getString("email"));
        mappedEmployee.setPhone(resultSet.getString("phone"));
        mappedEmployee.setAddress(resultSet.getString("address"));
        mappedEmployee.setEnabled(resultSet.getBoolean("enabled"));
        return mappedEmployee;
    }
}
