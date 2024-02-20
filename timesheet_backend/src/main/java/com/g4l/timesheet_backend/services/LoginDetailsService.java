package com.g4l.timesheet_backend.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.entities.Login;
import com.g4l.timesheet_backend.models.entities.LoginDetails;
import com.g4l.timesheet_backend.repositories.LoginRepository;
import lombok.NonNull;

@Service
public class LoginDetailsService implements UserDetailsService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // TODO: might want to surround this method with try catch for handling wherever its being called
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Optional<Login> loginDetails = loginRepository.findById(username);
        
        return loginDetails.map(LoginDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public String addUser(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        loginRepository.save(login);
        return "User added successfully";
    }
    
}
