package com.example.education_system.util;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.util.impl.FileUtilImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
class FileUtilImplTest {

    private List<Student> expectedStudents;
    private List<Log> expectedLogs;
    private FileUtilImpl testFileUtil;

    @BeforeEach()
    void setUp(){
        testFileUtil = new FileUtilImpl();
        expectedLogs = new ArrayList<>();
        expectedStudents = new ArrayList<>();

        expectedStudents.add(new Student(7921, 4));
        expectedStudents.add(new Student(7922, 3.5));
        expectedStudents.add(new Student(7938, 6));
        expectedStudents.add(new Student(7907, 6));

        expectedLogs.add(new Log("2/03/21, 14:12", "Course: Semantic Web", "System", "Course viewed", "The user with id '8429' viewed the course with id '130'."));
        expectedLogs.add(new Log("2/03/21, 14:11", "testEventContext", "testComponent", "testEventName", "testDescription"));
    }

    @Test
   public void givenValidFileWithStudentsResults_whenReadXlsxFile_thenReturnNotNullListWithData() throws IOException {
        List<Student> students  = testFileUtil.readXlsxFile("/Users/t.todorov/Documents/Java/testing/EducationSystem/src/test/testData/Test_data_Year 1.xlsx");

        Assertions.assertNotNull(students);
        for (int i = 0; i < students.size(); i++) {
            Assertions.assertEquals(students.get(i).getId(), expectedStudents.get(i).getId());
            Assertions.assertEquals(students.get(i).getResult(), expectedStudents.get(i).getResult());
        }
   }

    @Test
    public void givenValidFileWithStudentsActivities_whenReadXlsxFile_thenReturnNotNullListWithData()  throws IOException {
        List<Log> logs  = testFileUtil.readXlsxFile("/Users/t.todorov/Documents/Java/testing/EducationSystem/src/test/testData/Test_file_StudentsActivities.xlsx");

        Assertions.assertNotNull(logs);
        for (int i = 0; i < logs.size(); i++) {
            Assertions.assertEquals(logs.get(i).getTimestamp(), expectedLogs.get(i).getTimestamp());
            Assertions.assertEquals(logs.get(i).getEventContext(), expectedLogs.get(i).getEventContext());
            Assertions.assertEquals(logs.get(i).getComponent(), expectedLogs.get(i).getComponent());
            Assertions.assertEquals(logs.get(i).getEventName(), expectedLogs.get(i).getEventName());
            Assertions.assertEquals(logs.get(i).getDescription(), expectedLogs.get(i).getDescription());
        }
    }

    @Test
    public void givenInvalidFile_whenReadXlsxFile_thenThrowIOException() {
    Assertions.assertThrows(IOException.class, () -> {
        testFileUtil.readXlsxFile("invalid.xlsx");
        });
    }
}