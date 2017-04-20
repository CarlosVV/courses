package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.complex.StudentAttendance;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Service layer facade, hides away details of dataaccess layer from client.</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AttendanceService {
	
	void createStudentAttendanceSpreadsheetsForBlock(String blockId);
	
	void createStudentAttendanceSpreadsheetForOffering(String courseOfferingId);
	
	List<StudentAttendance> retrieveStudentAttendanceRecords(String courseOfferingId);
	
}
