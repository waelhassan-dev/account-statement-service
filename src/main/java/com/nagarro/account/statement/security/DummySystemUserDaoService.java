package com.nagarro.account.statement.security;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.nagarro.account.statement.security.SystemUserRole.ADMIN;
import static com.nagarro.account.statement.security.SystemUserRole.USER;


@Repository()
public class DummySystemUserDaoService implements SystemUserDao {

    private final PasswordEncoder passwordEncoder;

    public DummySystemUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<SystemUser> getUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(systemUser -> username.equals(systemUser.getUsername()))
                .findFirst();
    }

    private List<SystemUser> getApplicationUsers() {
        List<SystemUser> systemUsers = Lists.newArrayList(
                new SystemUser(
                        "admin",
                        passwordEncoder.encode("admin"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true),
                new SystemUser(
                        "user",
                        passwordEncoder.encode("user"),
                        USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true)
        );
        return systemUsers;
    }
}
