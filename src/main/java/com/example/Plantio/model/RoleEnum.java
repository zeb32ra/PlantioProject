package com.example.Plantio.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER, ADMIN, MANAGER;


    @Override
    public String getAuthority() {
        return name();
    }
}
