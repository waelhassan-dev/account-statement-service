package com.nagarro.account.statement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements UserDetailsService {

    private final SystemUserDao systemUserDao;

    @Autowired
    public SystemUserService(@Qualifier("dummySystemUserDaoService") SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return systemUserDao.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }
}
