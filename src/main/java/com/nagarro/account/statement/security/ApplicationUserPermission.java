package com.nagarro.account.statement.security;

public enum ApplicationUserPermission {

    PARAMETERIZE_READ("parameterize:read"),
    NON_PARAMETERIZE_READ("non_parameterize:read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
