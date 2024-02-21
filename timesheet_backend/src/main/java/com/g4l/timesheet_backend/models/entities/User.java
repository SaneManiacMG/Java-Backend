package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.g4l.timesheet_backend.models.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class User implements UserDetails {
    public User(String userId, String idNumber, String firstName, String lastName, String username, String email,
            String phoneNumber) {
        this.userId = userId;
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "user_id")
    String userId;
    @Column(name = "id_number", unique = true, nullable = false)
    String idNumber;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "user_name", unique = true, nullable = false)
    String username;
    @Column(unique = true, nullable = false)
    String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    String phoneNumber;
    @Column(name = "date_created")
    LocalDateTime dateCreated;
    @Column(name = "date_modified")
    LocalDateTime dateModified;
    @Column(name = "password", nullable = false)
    String password;
    @Column(name = "authorities", nullable = false)

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Role> authorities;

    @Column
    AccountType accountType;

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
