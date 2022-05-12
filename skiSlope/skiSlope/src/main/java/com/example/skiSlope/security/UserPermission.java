package com.example.skiSlope.security;

public enum UserPermission {
    GET_ALL_USERS("user:getall"),
    PERMISSION_TWO("COSTAM:WRITE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
