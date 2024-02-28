package com.g4l.timesheet_backend.services;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import com.g4l.timesheet_backend.models.requests.PasswordRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final ConsultantRepository consultantRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ConsultantRepository consultantRepository, ManagerRepository managerRepository,
                           PasswordEncoder passwordEncoder) {
        this.consultantRepository = consultantRepository;
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Object getUser(String userId) {
        // String cellPhoneNumberPattern = "^\\d{10}$";
        String idNumberPattern = "^\\d{13}$";
        String usernamePattern = "^[a-zA-Z0-9._-]{3,}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

        if (Pattern.matches(idNumberPattern, userId)) {
            if (consultantRepository.findByIdNumber(userId) != null)
                return consultantRepository.findByIdNumber(userId);
            if (managerRepository.findByIdNumber(userId) != null)
                return managerRepository.findByIdNumber(userId);
            return null;
        }
        ;

        if (Pattern.matches(usernamePattern, userId)) {
            if (consultantRepository.findByUserName(userId) != null)
                return consultantRepository.findByUserName(userId);
            if (managerRepository.findByUserName(userId) != null)
                return managerRepository.findByUserName(userId);
            return null;
        }
        ;

        if (Pattern.matches(emailPattern, userId)) {
            if (consultantRepository.findByEmail(userId) != null)
                return consultantRepository.findByEmail(userId);
            if (managerRepository.findByEmail(userId) != null)
                return managerRepository.findByEmail(userId);
            return null;
        }
        ;

        return null;
    }

    @Override
    public Object getUser(String username, String idNumber, String email) {
        if (getUser(username) != null)
            return getUser(username);
        if (getUser(idNumber) != null)
            return getUser(idNumber);
        if (getUser(email) != null)
            return getUser(email);
        return null;
    }

    @Override
    public Object userUpdater(UserRequest request, Object recordToUpdate) {
        Manager manager = null;
        if (recordToUpdate instanceof Manager) {
            manager = (Manager) recordToUpdate;
            manager.setId(request.getIdNumber());
            manager.setFirstName(request.getFirstName());
            manager.setLastName(request.getLastName());
            manager.setEmail(request.getEmail());
            manager.setPhoneNumber(request.getPhoneNumber());
            manager.setUserName(request.getUserName());
            manager.setDateModified(LocalDateTime.now());

            return manager;
        }

        Consultant consultant = null;
        if (recordToUpdate instanceof Consultant) {
            consultant = (Consultant) recordToUpdate;
            consultant.setId(request.getIdNumber());
            consultant.setFirstName(request.getFirstName());
            consultant.setLastName(request.getLastName());
            consultant.setEmail(request.getEmail());
            consultant.setPhoneNumber(request.getPhoneNumber());
            consultant.setUserName(request.getUserName());
            consultant.setDateModified(LocalDateTime.now());

            return consultant;
        }
        return null;
    }

    @Override
    public Object resetPassword(PasswordRequest passwordRequest) {
        Object userToUpdate = getUser(passwordRequest.getUserId());

        if (userToUpdate instanceof Consultant) {
            ((Consultant) userToUpdate).setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            ((Consultant) userToUpdate).setDateModified(LocalDateTime.now());
            consultantRepository.save((Consultant) userToUpdate);
            return userToUpdate;
        }

        if (userToUpdate instanceof Manager) {
            ((Manager) userToUpdate).setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
            ((Manager) userToUpdate).setDateModified(LocalDateTime.now());
            managerRepository.save((Manager) userToUpdate);
            return userToUpdate;
        }

        return null;
    }

    @Override
    public Object changePassword(PasswordRequest passwordRequest) {
        Object userToUpdate = getUser(passwordRequest.getUserId());

        if (userToUpdate instanceof Consultant) {
            if (passwordEncoder.matches(passwordRequest.getCurrentPassword(), ((Consultant) userToUpdate).getPassword())) {
                return resetPassword(passwordRequest);
            }
        }

        if (userToUpdate instanceof Manager) {
            if (passwordEncoder.matches(passwordRequest.getCurrentPassword(), ((Manager) userToUpdate).getPassword())) {
                return resetPassword(passwordRequest);
            }
        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (consultantRepository.findByUserName(username) != null)
            return consultantRepository.findByUserName(username);
        if (managerRepository.findByUserName(username) != null)
            return managerRepository.findByUserName(username);

        throw new UsernameNotFoundException("User not found");
    }

}
