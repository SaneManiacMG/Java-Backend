package com.g4l.timesheet_backend.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.g4l.timesheet_backend.models.enums.SequenceType;

@Service
public abstract class SequenceGenerator {
    public static String generateSequence(SequenceType sequenceType) {
        String sequence = sequenceTypeString(sequenceType) + dateString() + randomNumber();
        return sequence;
    }

    private static String sequenceTypeString(SequenceType sequenceType) {
        switch (sequenceType) {
            case CLIENT_ID:
                return "CL";
            case LOGBOOK_ID:
                return "LO";
            case MANAGER_ID:
                return "MA";
            case TEAM_ID:
                return "TE";
            case CONSULTANT_ID:
                return "CO";
            default:
                break;
        }
        return "";
    }

    private static String dateString() {
        String dateTime = DateTimeFormatter.ofPattern("yyMMddhhmmss").format(LocalDateTime.now());
        return dateTime;
    }

    private static String randomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(9999 - 1000) + 1000;
        return String.valueOf(randomNumber);
    }
}
