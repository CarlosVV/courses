package edu.mum.cs.projects.attendance.util.parser;

import java.time.ZoneId;
import java.util.function.Function;

import org.apache.poi.ss.usermodel.Row;

import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;

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
public class AcademicBlockParser implements Function<Row, AcademicBlock> {

	@Override
	public AcademicBlock apply(Row row) {
		AcademicBlock block = new AcademicBlock();

		block.setId(row.getCell(0).getStringCellValue());
        block.setName(row.getCell(1).getStringCellValue());
        block.setStartDate(row.getCell(2).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        block.setEndDate(row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        block.setSemester(row.getCell(4).getStringCellValue());
        
        return block;
	}

}
