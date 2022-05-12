package com.example.skiSlope.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    CUSTOMER(Sets.newHashSet()),
    MANAGER(Sets.newHashSet(UserPermission.GET_ALL_USERS));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities =
                getPermissions()
                        .stream()
                        .map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
                        .collect((Collectors.toSet()));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    public String getUserRoleName(){
        return "ROLE_" + this.name();
    }
}