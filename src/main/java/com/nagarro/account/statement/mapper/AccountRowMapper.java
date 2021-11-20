package com.nagarro.account.statement.mapper;

import com.nagarro.account.statement.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import static com.nagarro.account.statement.util.SHA256Digest.digest;

public class AccountRowMapper implements RowMapper<Account> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Account(
                resultSet.getLong("ID"),
                resultSet.getString("account_type"),
                digest(resultSet.getString("account_number"))
        );
    }

}
