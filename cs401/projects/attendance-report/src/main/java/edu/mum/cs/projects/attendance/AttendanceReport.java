package edu.mum.cs.projects.attendance;

import edu.mum.cs.projects.attendance.service.AttendanceService;
import edu.mum.cs.projects.attendance.service.ServiceFactory;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Run reports from here!</p>
 *
 * @author Payman Salek
 * 
 * @version 2.0.0
 * @since 1.0.0
 * 
 */
public class AttendanceReport {

	public static void main(String[] args) {
		AttendanceService service = ServiceFactory.getAttendanceService();
		
		service.createStudentAttendanceSpreadsheetsForBlock("2017-04A-04D");
		
		System.out.println("\nAttendance Report App finished executing!");		
	}
	
}
