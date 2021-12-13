package com.nagarro.account.statement.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.nagarro.account.statement.security.UserPermission.NON_RESTRICTED_READ;
import static com.nagarro.account.statement.security.UserPermission.RESTRICTED_READ;


public enum SystemUserRole {

    USER(Sets.newHashSet(RESTRICTED_READ)),
    ADMIN(Sets.newHashSet(NON_RESTRICTED_READ));

    private final Set<UserPermission> permissions;

    SystemUserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissionsList = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissionsList.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsList;
    }
}
