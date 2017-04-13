package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.cs.projects.attendance.domain.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.complex.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Session;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.util.AttendanceReportUtil;
import edu.mum.cs.projects.attendance.util.BarcodeScannerUtil;
import edu.mum.cs.projects.attendance.util.SpreadsheetUtil;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>DataAccess Service implementation.</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
public class DataAccessServiceImpl implements DataAccessService {
	
	@Override
	public List<Course> getAllCourses() {
		return SpreadsheetUtil.getCourseList();
	}

	@Override
	public List<Session> getAllSessions() {
		return SpreadsheetUtil.getSessionList();
	}

	@Override
	public List<AcademicBlock> getAllAcademicBlocks() {
		return SpreadsheetUtil.getAcademicBlockList();
	}

	
	@Override
	public List<Registration> getAllRegistrations() {
		return SpreadsheetUtil.getAllRegistrations();
	}

	@Override
	public List<CourseOffering> getAllCourseOfferings() {		
		List<CourseOffering> offerings = SpreadsheetUtil.getAllCourseOfferings();
		List<Session> sessions = getAllSessions();
		
		offerings.stream()
			.peek(co -> co.setSessions(sessions.stream().filter(s -> s.getBlock().equals(co.getBlock())).collect(Collectors.toSet())))
			.collect(Collectors.toList());
		
		return offerings;	
	}
	
	@Override
	public List<Student> getAllStudents() {
		return SpreadsheetUtil.getStudentList();
	}

	@Override
	public List<BarcodeRecord> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate) {
		return BarcodeScannerUtil.getBarcodeRecordsList(startDate, endDate);
	}

	@Override
	public void createAttendanceReportSpreadsheet(List<StudentAttendance> studentAttendanceRecords) {
		AttendanceReportUtil.createAttendanceReportSpreadsheet(studentAttendanceRecords);
	}

}
