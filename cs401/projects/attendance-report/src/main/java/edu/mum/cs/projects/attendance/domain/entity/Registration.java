package edu.mum.cs.projects.attendance.domain.entity;

/**
 * <h1>Maharishi University of Management<br/>
 * Computer Science Department</h1>
 * 
 * <p>
 * Domain entity. Simple POJO.
 * </p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class Registration {
	
	private int id;

	private int sequence;

	private Student student;

	private CourseOffering courseOffering;

	private boolean[] attendance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseOffering getCourseOffering() {
		return courseOffering;
	}

	public void setCourseOffering(CourseOffering courseOffering) {
		this.courseOffering = courseOffering;
	}

	public boolean[] getAttendance() {
		return attendance;
	}

	public void setAttendance(boolean[] attendance) {
		this.attendance = attendance;
	}

}
