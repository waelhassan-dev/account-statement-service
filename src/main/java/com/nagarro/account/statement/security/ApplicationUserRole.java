package com.nagarro.account.statement.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;


import java.util.Set;
import java.util.stream.Collectors;

import static com.nagarro.account.statement.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {

    USER(Sets.newHashSet(NON_PARAMETERIZE_READ)),
    ADMIN(Sets.newHashSet(PARAMETERIZE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
