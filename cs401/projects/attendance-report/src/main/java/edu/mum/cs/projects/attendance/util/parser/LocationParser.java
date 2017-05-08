package edu.mum.cs.projects.attendance.util.parser;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.Location;

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
public class LocationParser implements Function<Row, Location> {
	
    @Override
    public Location apply(Row row) {
        Location l = new Location();
        l.setId(row.getCell(0).getStringCellValue());
        l.setName(row.getCell(1).getStringCellValue());
        
        return l;
    }
}
