package com.g4l.timesheet_backend.models.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginDetails implements UserDetails {
    private String userId;
    private String password;
    private List<GrantedAuthority> authorities;

    public LoginDetails(Login login) {
        userId = login.getId();
        password = login.getPassword();
        authorities = Arrays.stream(login.getRoles().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList()); 
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
        
}
