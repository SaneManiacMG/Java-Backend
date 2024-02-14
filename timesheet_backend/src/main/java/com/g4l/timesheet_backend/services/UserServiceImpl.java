package com.g4l.timesheet_backend.services;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.interfaces.UserService;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.entities.Manager;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.repositories.ManagerRepository;

@Service
public class UserServiceImpl implements UserService {
    private final ConsultantRepository consultantRepository;
    private final ManagerRepository managerRepository;

    public UserServiceImpl(ConsultantRepository consultantRepository, ManagerRepository managerRepository) {
        this.consultantRepository = consultantRepository;
        this.managerRepository = managerRepository;
    }

    public Object getUser(String userId) {
        String cellPhoneNumberPattern = "^\\d{10}$";
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
            if (consultantRepository.findByUsername(userId) != null)
                return consultantRepository.findByUsername(userId);
            if (managerRepository.findByUsername(userId) != null)
                return managerRepository.findByUsername(userId);
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
            manager.setUsername(request.getUsername());
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
            consultant.setUsername(request.getUsername());
            consultant.setDateModified(LocalDateTime.now());

            return consultant;
        }
        return null;
    }

}
