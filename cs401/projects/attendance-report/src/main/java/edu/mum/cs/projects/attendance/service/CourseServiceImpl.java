package edu.mum.cs.projects.attendance.service;

import java.util.LinkedList;
import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;

public class CourseServiceImpl implements CourseService {
	
	DataAccessService dataAccessService = ServiceFactory.getDataAccessService();

	@Override
	public List<CourseOffering> getCourseOfferingListbyBlockId(String blockId) {
		List<CourseOffering> offerings = dataAccessService.getAllCourseOfferings();
		
		List<CourseOffering> results = new LinkedList<>();
		for(CourseOffering offering : offerings) {
			if(offering.getBlock().getId().equals(blockId)) {
				results.add(offering);
			}
		}
		
		return results;
	}

}
