package com.example.education_system.service.impl;

import com.example.education_system.domain.Course;
import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.dto.LogAllPropertiesDto;
import com.example.education_system.exception.LogNotFoundException;
import com.example.education_system.exception.ObjectNotFoundException;
import com.example.education_system.repository.CourseRepository;
import com.example.education_system.repository.LogRepository;
import com.example.education_system.repository.StudentRepository;
import com.example.education_system.service.LogService;
import com.example.education_system.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final static String studentsActivitiesLogFilePath = "./Logs_Course A_StudentsActivities.xlsx";

    private  static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    private final FileUtil fileUtil;
    private final LogRepository logRepository;

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public LogServiceImpl(FileUtil fileUtil, LogRepository logRepository, StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper modelMapper) {
        this.fileUtil = fileUtil;
        this.logRepository = logRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedLogs() throws IOException {
        logger.info("Seeding logs...");

        List<Log> studentActivitiesLog = fileUtil.readXlsxFile(studentsActivitiesLogFilePath);

      for(Log log : studentActivitiesLog){
          Pattern pattern = Pattern.compile("'[0-9]*'");
          Matcher matcher = pattern.matcher(log.getDescription());

          int userId=0;
          int courseId=0;
          int hardCodedCounter=0;


          while(matcher.find() && hardCodedCounter<2){
              String str = matcher.group();

              str= str.replace("'","");
              System.out.println(str);
              if(hardCodedCounter==0){
                  userId=Integer.parseInt(str);
              }else {
                  courseId=Integer.parseInt(str);
              }
              hardCodedCounter++;
          }

          if(userId==0 || courseId==0){
                throw new LogNotFoundException("Invalid user or course id for concrete log");
          }

          Student studentInDb = studentRepository.findById(userId).orElse(null);

          log.setStudent(studentInDb);

          Course courseInDb = courseRepository.findById(courseId).orElse(null);


          if(courseInDb==null){
              logger.info("There is not such course in the database");

              courseInDb = new Course();
              courseInDb.setId(courseId);
              courseInDb.setStudents(Collections.singletonList(studentInDb));
              courseInDb.setLogs(new HashSet<>());
              courseInDb = courseRepository.save(courseInDb);

          }else {
              logger.info("There is such course in the database");

              List<Student> students = courseInDb.getStudents();

              students.add(studentInDb);

              courseInDb.setStudents(students);

              courseInDb = courseRepository.save(courseInDb);
          }

          log.setCourse(courseInDb);
          logRepository.saveAndFlush(log);
      }


        logger.info("Logs successfully seeded");
    }

    @Override
    public Set<LogAllPropertiesDto> getAllLogs() {
       return logRepository
               .findAll()
               .stream()
               .map(log-> modelMapper.map(log, LogAllPropertiesDto.class))
               .collect(Collectors.toSet());
    }

    @Override
    public LogAllPropertiesDto getOne(int id) {
       return modelMapper.map(logRepository.findById(id).orElseThrow(() -> {
            throw new LogNotFoundException("cannot find log with id "+ id);
        }), LogAllPropertiesDto.class);

    }

    @Override
    public double getRelativeFrequency(String component) {
        return logRepository.getCountOfLogsByComponent(component) / logRepository.getCountOfLogs();
    }
}
