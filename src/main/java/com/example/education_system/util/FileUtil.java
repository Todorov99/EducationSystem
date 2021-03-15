package com.example.education_system.util;

import com.example.education_system.domain.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileUtil {

   <T> List<T> readXlsxFile(String filePath) throws IOException;
}
