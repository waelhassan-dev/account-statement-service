package com.nagarro.account.statement.mapper;

import com.nagarro.account.statement.model.Statement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StatementRowMapper implements RowMapper<Statement> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public Statement mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Statement(
                resultSet.getLong("ID"),
                resultSet.getLong("account_id"),
                LocalDate.parse(resultSet.getString("datefield"), formatter),
                resultSet.getDouble("amount")
        );
    }

}
