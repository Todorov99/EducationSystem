package util.impl;

import com.example.education_system.domain.Log;
import com.example.education_system.domain.Student;
import com.example.education_system.util.impl.FileUtilImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class FileUtilImplTest {

    private List<Student> expectedStudents = new ArrayList<>();
    private List<Log> expectedLogs = new ArrayList<>();
    private FileUtilImpl testFileUtil = new FileUtilImpl();

    @BeforeEach()
    void setUp(){
        expectedStudents.add(new Student(7921, 4));
        expectedStudents.add(new Student(7922, 3.5));
        expectedStudents.add(new Student(7938, 6));
        expectedStudents.add(new Student(7907, 6));

        expectedLogs.add(new Log("2/03/21, 14:12", "Course: Semantic Web", "System", "Course viewed", "The user with id '8429' viewed the course with id '130'."));
        expectedLogs.add(new Log("2/03/21, 14:11", "testEventContext", "testComponent", "testEventName", "testDescription"));
    }

    @Test
   public void shouldReturnValidNotNullListOfStudentWithResultsReadFromXLSFile() throws IOException {
        List<Student> students  = testFileUtil.readXlsxFile("/Users/t.todorov/Documents/Java/testing/EducationSystem/src/test/testData/Test_data_Year 1.xlsx");

        Assertions.assertNotNull(students);
        for (int i = 0; i < students.size(); i++) {
            Assertions.assertEquals(students.get(i).getId(), expectedStudents.get(i).getId());
            Assertions.assertEquals(students.get(i).getResult(), expectedStudents.get(i).getResult());
        }
   }

    @Test
    public void shouldReturnValidNotNullListOfLogsReadFromXLSFile() throws IOException {
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
    public void shouldThrowErrorWhenThereIsNotSuchFile() {
    Assertions.assertThrows(IOException.class, () -> {
        testFileUtil.readXlsxFile("invalid.xlsx");
        });
    }
}