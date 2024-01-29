package com.g4l.timesheet_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.interfaces.LogbookService;
import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.repositories.LogbookRepository;

@Service
public class LogbookServiceImpl implements LogbookService {
    private LogbookRepository logbookRepository;

    public LogbookServiceImpl(LogbookRepository logbookRepository) {
        this.logbookRepository = logbookRepository;
        
    }

    @Override
    public Logbook createLogbook(Logbook logbook) {
        return logbookRepository.save(logbook);
    }

    @Override
    public Logbook updateLogbook(Logbook logbook) {
        return logbookRepository.save(logbook);
    }

    @Override
    public Logbook getLogbookById(String logbookId) {
        return logbookRepository.findById(logbookId).orElse(null);
    }

    @Override
    public List<Logbook> getAllLogbooks() {
        return logbookRepository.findAll();
    }

    @Override
    public String deleteLogbook(String logbookId) {
        logbookRepository.deleteById(logbookId);
        return "Logbook deleted";
    }

    @Override
    public List<Logbook> getLogbooksByConsultantId(String consultantId) {
        return logbookRepository.findLogbookByConsultantId(consultantId);
    }

    @Override
    public List<Logbook> getLogbooksByManagerId(String managerId) {
        return logbookRepository.findLogbookByManagerId(managerId);
    }
    
}
