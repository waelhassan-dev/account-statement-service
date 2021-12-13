package com.nagarro.account.statement.security;

public enum UserPermission {

    RESTRICTED_READ("restricted:read"),
    NON_RESTRICTED_READ("non_restricted:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
