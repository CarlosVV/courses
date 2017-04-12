package edu.mum.cs.projects.attendance.util.parser;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.util.SpreadsheetUtil;

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
public class RegistrationParser implements Function<Row, Registration> {

	@Override
	public Registration apply(Row row) {
		Registration registration = new Registration();

		//registration.setId((int)(row.getCell(0).getNumericCellValue()));
        registration.setSequence((int)(row.getCell(1).getNumericCellValue()));
        
        Student student = SpreadsheetUtil.getStudentList()
        		.stream()
        		.filter(s -> s.getId().equals(row.getCell(2).getStringCellValue()))
        		.findFirst()
        		.get();
        registration.setStudent(student);
        
        CourseOffering courseOffering = SpreadsheetUtil.getAllCourseOfferings()
        		.stream()
        		.filter(s -> s.getId().equals(row.getCell(3).getStringCellValue()))
        		.findFirst()
        		.get();
        registration.setCourseOffering(courseOffering);
                     
        return registration;
	}

}
