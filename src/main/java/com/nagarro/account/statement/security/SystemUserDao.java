package com.nagarro.account.statement.security;

import java.util.Optional;

public interface SystemUserDao {

    Optional<SystemUser> getUserByUsername(String username);
}
