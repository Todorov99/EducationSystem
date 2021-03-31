package com.example.education_system.service;

import com.example.education_system.dto.LogAllPropertiesDto;

import java.io.IOException;
import java.util.Set;

public interface LogService {

    void seedLogs() throws IOException;

    Set<LogAllPropertiesDto> getAllLogs();

    LogAllPropertiesDto getOne(int id);
}
