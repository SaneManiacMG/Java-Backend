package com.g4l.timesheet_backend.models.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.g4l.timesheet_backend.models.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class User implements UserDetails {
    public User(String id, String idNumber, String firstName, String lastName, String userName, String email,
            String phoneNumber) {
        this.id = id;
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User() {
        super();
        this.accountType = AccountType.UNVERIFIED;
    }

    @Id
    @Column(name = "user_id")
    String id;
    @Column(name = "id_number", unique = true, nullable = false)
    String idNumber;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(name = "user_name", unique = true, nullable = false)
    String userName;
    @Column(unique = true, nullable = false)
    String email;
    @Column(name = "phone_number", unique = true, nullable = false)
    String phoneNumber;
    @Column(name = "date_created")
    LocalDateTime dateCreated;
    @Column(name = "date_modified")
    LocalDateTime dateModified;
    @Column(name = "password")
    String password;
    @Column(name = "authorities")

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Role> authorities;

    @Column
    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //TODO: Figure out which username is being pulled, userId or username
    @Override
    public String getUsername() {
        return id;
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
