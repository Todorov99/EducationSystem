package com.example.education_system.util.impl;

import com.example.education_system.domain.Student;
import com.example.education_system.service.impl.StudentServiceImpl;
import com.example.education_system.util.FileUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtilImpl implements FileUtil {

    private  static final Logger logger = LoggerFactory.getLogger(FileUtilImpl.class);

    @Override
    public List<Student> readXlsxFile(String filePath) throws IOException {
        logger.info(String.valueOf(System.out.format("Reading %s Xlsx file...", filePath)));

        FileInputStream file = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> sheetIterator = sheet.iterator();

        List<Student> students = new ArrayList<>();

        while (sheetIterator.hasNext()) {
            Row row = sheetIterator.next();

                switch (row.getCell(0).getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        logger.info(String.valueOf(System.out.format("Cell STRING %s - %s ", row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue())));
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        logger.info(String.valueOf(System.out.format("Cell NUMERIC %f - %f", row.getCell(0).getNumericCellValue(), row.getCell(1).getNumericCellValue())));
                        Student student = new Student(Integer.valueOf((int)row.getCell(0).getNumericCellValue()), row.getCell(1).getNumericCellValue());

                        logger.info(student.toString());
                        if (filePath.contains("Year 1")) {
                            student.setYear(Integer.valueOf(1));
                            students.add(student);
                            continue;
                        }

                        student.setYear(2);
                        students.add(student);

                        break;
                }

        }

        logger.info(String.valueOf(System.out.format("%s Xlsx file successfully read", filePath)));

        return students;
    }


}
