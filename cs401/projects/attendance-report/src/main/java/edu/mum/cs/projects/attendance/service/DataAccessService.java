package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.List;

import edu.mum.cs.projects.attendance.domain.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.complex.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Session;
import edu.mum.cs.projects.attendance.domain.entity.Student;

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
public interface DataAccessService {

	List<Student> getAllStudents();
	
	List<BarcodeRecord> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate);
	
	void createAttendanceReportSpreadsheet(List<StudentAttendance> studentAttendanceRecords);

	List<Course> getAllCourses();

	List<Session> getAllSessions();

	List<AcademicBlock> getAllAcademicBlocks();
	
	List<Registration> getAllRegistrations();
	
	List<CourseOffering> getAllCourseOfferings();
	
}
