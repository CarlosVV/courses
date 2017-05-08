package edu.mum.cs.projects.attendance.util.parser;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.Student;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Excel row parser/mapper. Converts an Excel row into an object.
 * Note: This file is tightly coupled with the structure of the input Excel sheet.</p>
 *
 * @author Hong An Nguyen
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class StudentParser implements Function<Row, Student> {
	
    @Override
    public Student apply(Row row) {
        Student student = new Student();

        student.setId(row.getCell(1).getStringCellValue());
        student.setFirstName(row.getCell(2).getStringCellValue());
        student.setLastName(row.getCell(3).getStringCellValue());
        student.setBarCode(row.getCell(4).getStringCellValue());
        
        return student;
    }
}
