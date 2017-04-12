package edu.mum.cs.projects.attendance.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import edu.mum.cs.projects.attendance.domain.complex.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Session;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Utility that generates an Excel spreadsheet.
 * Resulting Excel file will be saved under {@value #ROOT_DIRECTORY}</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class AttendanceReportUtil {
	
	private static final String ERROR_SHEET = "Errors";
	private static final String MAIN_SHEET = "Attendance";
	private static final String ROOT_DIRECTORY = "src/main/resources/reports/";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void createAttendanceReportSpreadsheet(List<StudentAttendance> studentAttendanceRecords) {
		if(null == studentAttendanceRecords || studentAttendanceRecords.isEmpty()) {
			return; // Do nothing
		}
		
		CourseOffering courseOffering = studentAttendanceRecords.get(0).getCourseOffering();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(MAIN_SHEET);
		
		workbook.getCellStyleAt(0).setAlignment(HorizontalAlignment.CENTER);
		
		Row row;
		Cell cell;
		int rowIndex;
		int columnIndex;
		
		// Create headers
		rowIndex = 0;
		row = sheet.createRow(rowIndex++);
		columnIndex = 0;
		
		row.createCell(columnIndex++).setCellValue("Index");
		row.createCell(columnIndex++).setCellValue("StudentId");
		row.createCell(columnIndex++).setCellValue("FirstName");
		row.createCell(columnIndex++).setCellValue("LastName");		
		row.createCell(columnIndex++).setCellValue("TotalPresent");
		row.createCell(columnIndex++).setCellValue("Percent");
		row.createCell(columnIndex++).setCellValue("ExtraCredit");
		
		for (Session session : courseOffering.getSessions()) {
			row.createCell(columnIndex++).setCellValue(session.getDate().format(FORMATTER));
		}
		
		// Create content
		rowIndex = 1;
		List<StudentAttendance> errorRecords = new ArrayList<StudentAttendance>();
		for (StudentAttendance sa : studentAttendanceRecords) {
			if(null == sa.getStudent().getBarCode() || null == sa.getStudent().getFirstName()) {
				errorRecords.add(sa);
				continue;
			}
			
			row = sheet.createRow(rowIndex++);

			columnIndex = 0;
			row.createCell(columnIndex++).setCellValue(rowIndex - 1);
			row.createCell(columnIndex++).setCellValue(sa.getStudent().getId());
			row.createCell(columnIndex++).setCellValue(sa.getStudent().getFirstName());
			row.createCell(columnIndex++).setCellValue(sa.getStudent().getLastName());

			char startColumn = 'H'; // First column for attendance cells. SUM goes over H to (H + size - 1)
			row.createCell(columnIndex++).setCellFormula("SUM(" + startColumn + rowIndex +":" + calculateColumn(startColumn, sa.getAttendance().size()) + rowIndex + ")");
			row.createCell(columnIndex++).setCellFormula("ROUND(100*E" + rowIndex + "/" + sa.getCourseOffering().getRequiredSessions() + ", 1)");
			row.createCell(columnIndex++).setCellFormula(String.format("IF(F%1$d >= 90, 1.5, IF(F%1$d >= 80, 1, IF(F%1$d >= 70, 0.5, 0)))", rowIndex));
			
			for (boolean present : sa.getAttendance()) {
				cell = row.createCell(columnIndex++);
				if (present) {
					cell.setCellValue(1);
				} else {
					cell.setCellValue(0);
				}
			}
		}
		
		// Generate error report
		sheet = workbook.createSheet(ERROR_SHEET);
		rowIndex = 0;
		row = sheet.createRow(rowIndex++);
		columnIndex = 0;
		row.createCell(columnIndex++).setCellValue("StudentID");
		row.createCell(columnIndex++).setCellValue("Issue");
		
		for(StudentAttendance sa : errorRecords) {
			row = sheet.createRow(rowIndex++);
			columnIndex = 0;
			row.createCell(columnIndex++).setCellValue(sa.getStudent().getId());
			
			String errorMessage = "";
			if(null == sa.getStudent().getFirstName()) {
				errorMessage = "No student record was found for this student Id.";
			}
			else if(null == sa.getStudent().getBarCode()) {
				errorMessage = "No barcode was found for this student.";
			}
			row.createCell(columnIndex++).setCellValue(errorMessage);
		}

		
		int year = courseOffering.getBlock().getStartDate().getYear();
		String directory = ROOT_DIRECTORY + year + "/" + courseOffering.getBlock().getId() + "/";
		File file = new File(directory);
		file.mkdirs();
		
		String fileName = directory + courseOffering.getCourse().getId() + "-" + courseOffering.getFaculty().replaceAll(" ",  "") + ".xlsx";

		FileOutputStream out;
		try {
			out = new FileOutputStream(fileName);
			workbook.write(out);
			out.close();
			workbook.close();			
			//System.out.println("Saving attendance report to: " + fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String calculateColumn(char start, int size) {
		char[] array;
		
		size = size - 1;
		
		if(('Z' - start) < size) {
			array = new char[2];
			size -= ('Z' - start + 1);
			array[0] = (char)('A' + size / 26);
			array[1] = (char)('A' + size % 26);
		}
		else {
			array = new char[1];
			array[0] = (char)(start + size);
		}
		
		return new String(array);		
	}
}
