package edu.mum.cs.projects.attendance.service;

import java.util.List;
import java.util.stream.Collectors;

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
public class CourseServiceImpl implements CourseService {
	
	private DataAccessService dataAccessService = ServiceFactory.getDataAccessService();

	@Override
	public List<Course> getAllCourses() {
		return dataAccessService.getAllCourses();
	}

	@Override
	public List<Session> getAllSessions() {
		return dataAccessService.getAllSessions();
	}
	@Override
	public List<AcademicBlock> getAllAcademicBlocks() {
		return dataAccessService.getAllAcademicBlocks();
	}

	@Override
	public List<CourseOffering> getCourseOfferings() {
		return dataAccessService.getAllCourseOfferings();
	}

	@Override
	public List<CourseOffering> getCourseOfferings(String blockId) {
		AcademicBlock block = getAcademicBlock(blockId);
		
		List<CourseOffering> offerings = getCourseOfferings();
		
		return offerings.stream().filter(offering -> offering.getBlock().equals(block)).collect(Collectors.toList());
	}

	@Override
	public List<Session> getSessions(String courseOfferingId) {
		return getCourseOffering(courseOfferingId).getSessions();
	}

	@Override
	public CourseOffering getCourseOffering(String courseOfferingId) {
		return getCourseOfferings().stream().filter(o -> o.getId().equals(courseOfferingId)).findFirst().get();
	}

	@Override
	public AcademicBlock getAcademicBlock(String blockId) {
		return getAllAcademicBlocks().stream().filter(b -> b.getId().equals(blockId)).findFirst().get();
	}

	@Override
	public List<Registration> getRegistrationList(String courseOfferingId) {
		List<Registration> registrations = dataAccessService.getAllRegistrations();
		
		return registrations.stream()
				.filter(r -> r.getCourseOffering().getId().equals(courseOfferingId))
				.collect(Collectors.toList());
	}

}
