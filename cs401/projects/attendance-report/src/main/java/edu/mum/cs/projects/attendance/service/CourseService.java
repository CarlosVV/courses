package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;

public interface CourseService {

	List<CourseOffering> getCourseOfferingListbyBlockId(String blockId);
	
}
