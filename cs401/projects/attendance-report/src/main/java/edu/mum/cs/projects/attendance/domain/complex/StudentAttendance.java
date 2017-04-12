package edu.mum.cs.projects.attendance.domain.complex;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Student;

/**
 * <h1>Maharishi University of Management<br/>Computer Science Department</h1>
 * 
 * <p>Aggregate structure that holds the results of student attendance.</p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class StudentAttendance {
	private Student student;
	private CourseOffering courseOffering;
	private List<Boolean> attendance;

	public StudentAttendance() {
		attendance = new ArrayList<Boolean>();
	}

	public StudentAttendance(Student student, CourseOffering courseOffering) {
		this();
		this.student = student;
		this.courseOffering = courseOffering;
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

	public List<Boolean> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<Boolean> attendance) {
		this.attendance = attendance;
	}

	public long getMeditationCount() {
		return attendance.stream().filter(a -> a).count();
	}

	public double getMeditaionPercentage() {
		return 100 * getMeditationCount() / courseOffering.getRequiredSessions();
	}

	public double getMeditationExtraGrade() {
		double percentage = getMeditaionPercentage();

		if (percentage >= 90.0) {
			return 1.5;
		}
		if (percentage >= 80.0) {
			return 1.0;
		}
		if (percentage >= 70.0) {
			return 0.5;
		}

		return 0.0;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("Attendance [");
		sb.append(getStudent().getId());
		sb.append(" -> ");

		int presentCount = 0;
		for (boolean present : attendance) {
			if (present) {
				++presentCount;
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}

		sb.append(String.format("Total = %2d or ", presentCount));
		sb.append(String.format("%5.1f", (float) (100 * presentCount) / courseOffering.getRequiredSessions()));
		sb.append("%]");

		return sb.toString();
	}
}
