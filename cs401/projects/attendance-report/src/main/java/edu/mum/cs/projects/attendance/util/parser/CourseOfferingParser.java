package edu.mum.cs.projects.attendance.util.parser;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Location;
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
public class CourseOfferingParser implements Function<Row, CourseOffering> {
	
    @Override
    public CourseOffering apply(Row row) {
        CourseOffering offering = new CourseOffering();

        offering.setId(row.getCell(0).getStringCellValue());
        Course course = SpreadsheetUtil
                .getCourseList()
                .stream()
                .filter(c -> c.getId().equals(row.getCell(1).getStringCellValue()))
                .findFirst()
                .get();
        offering.setCourse(course);
        
        AcademicBlock block = SpreadsheetUtil
                .getAcademicBlockList()
                .stream()
                .filter(b -> b.getId().equals(row.getCell(2).getStringCellValue()))
                .findFirst()
                .get();
        offering.setBlock(block);
        
        offering.setFaculty(row.getCell(3).getStringCellValue());
        
        Location location = SpreadsheetUtil
                .getLocationList()
                .stream()
                .filter(l -> l.getId().equals(row.getCell(4).getStringCellValue()))
                .findFirst()
                .get();
        offering.setLocation(location);
        
        offering.setRequiredSessions((int)(row.getCell(5).getNumericCellValue()));       
        
        return offering;
    }
}
