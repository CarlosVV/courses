package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;

public class AttendanceServiceImpl implements AttendanceService {
	CourseService courseService = ServiceFactory.getCourseService();
	
	@Override
	public void createStudentAttendanceSpreadsheetsForBlock(String blockId) {
		// 1) Using blockId, get the list of course offerings for that particular block
		List<CourseOffering> offerings = courseService.getCourseOfferingListbyBlockId(blockId);
		offerings.forEach(System.out::println);
		
		// 2) Loop over all the course offerings
		
		// 3) For each course offering, get the list of registrations for that course from 
		// course service
		
		// 4) For each registration object, get the student
		
		// 5) Get the list of barcode-records for each student (filter based on start and end date of the block)

		// 6) Get the list of sessions for the block from course service
		
		// 7) Create an attendance list or array based on the number of sessions in that block
		
		// 8) For each student, loop over all sessions and determine (from the barcode records 
		// list) if the student has been present for that session
		
		// 9) Save the attendance info for each course offering in a separate spreadsheet
		// Note: Each course offering will have a list of students
	}

}
