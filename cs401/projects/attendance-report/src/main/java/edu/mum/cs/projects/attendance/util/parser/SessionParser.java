package edu.mum.cs.projects.attendance.util.parser;

import java.time.ZoneId;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Session;
import edu.mum.cs.projects.attendance.domain.entity.Timeslot;
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
public class SessionParser implements Function<Row, Session> {
	
    @Override
    public Session apply(Row row) {
        Session session = new Session();

        session.setId((int)(row.getCell(0).getNumericCellValue()));
        
        AcademicBlock block = SpreadsheetUtil.getAcademicBlockList()
                .stream()
                .filter(b -> b.getId().equals(row.getCell(1).getStringCellValue()))
                .findFirst()
                .get();
        
        session.setBlock(block);
        
        Timeslot ts = SpreadsheetUtil.getTimeslotList()
                .stream()
                .filter(timeslot -> timeslot.getAbbreviation().equals(row.getCell(2).getStringCellValue()))
                .findFirst()
                .get();
        
        session.setTimeslot(ts);
        
        session.setDate(row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        
        return session;
    }
}
