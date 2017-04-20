package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Registration;
import edu.mum.cs.projects.attendance.domain.entity.Session;

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
public interface CourseService {
	
	List<Course> getAllCourses();

	List<AcademicBlock> getAllAcademicBlocks();

	List<CourseOffering> getCourseOfferings();

	List<CourseOffering> getCourseOfferings(String blockId);

	List<Session> getAllSessions();

	List<Session> getSessions(String courseOfferingId);

	List<Registration> getRegistrationList(String courseOfferingId);

	AcademicBlock getAcademicBlock(String blockId);

	CourseOffering getCourseOffering(String courseOfferingId);
}
