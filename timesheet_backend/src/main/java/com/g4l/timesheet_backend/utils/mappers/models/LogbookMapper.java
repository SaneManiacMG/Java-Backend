package com.g4l.timesheet_backend.utils.mappers.models;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.g4l.timesheet_backend.models.entities.Logbook;
import com.g4l.timesheet_backend.models.requests.LogbookSubmissionRequest;
import com.g4l.timesheet_backend.models.responses.LogbookResponse;

@Service
public class LogbookMapper {
    private final ModelMapper modelMapper;

    public LogbookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Logbook logbookSubmissionRequestToLogbook(LogbookSubmissionRequest logbookSubmissionRequest) {
        return modelMapper.map(logbookSubmissionRequest, Logbook.class);
    }

    public LogbookResponse logbookToLogbookResponse(Logbook logbook) {
        return modelMapper.map(logbook, LogbookResponse.class);
    }
}
