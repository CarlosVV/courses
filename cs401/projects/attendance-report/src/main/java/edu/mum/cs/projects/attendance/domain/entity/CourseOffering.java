package edu.mum.cs.projects.attendance.domain.entity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Domain entity. Simple POJO. Course offering is an instance of a course.</p>
 *
 * @author Hong An Nguyen
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class CourseOffering {
	
	private String id;
	
	private Course course;
	
	private Set<Session> sessions;
	
	private AcademicBlock block;
	
	private Location location;
	
	private String faculty;
	
	private int requiredSessions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Session> getSessions() {
		Comparator<Session> byDate = (s1, s2) -> s1.getDate().compareTo(s2.getDate());
		return sessions.stream().sorted(byDate).collect(Collectors.toList());
	}

	public void setSessions(Set<Session> sessions) {
		this.sessions = sessions;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public AcademicBlock getBlock() {
		return block;
	}

	public void setBlock(AcademicBlock block) {
		this.block = block;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getRequiredSessions() {
		return requiredSessions;
	}

	public void setRequiredSessions(int requiredSessions) {
		this.requiredSessions = requiredSessions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseOffering other = (CourseOffering) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CourseOffering [id=" + id + ", course=" + course + ", block=" + block + ", location=" + location
				+ ", faculty=" + faculty + ", requiredSessions=" + requiredSessions + "]";
	}

}
